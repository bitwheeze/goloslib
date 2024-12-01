package bitwheeze.golos.goloslib;

import bitwheeze.golos.goloslib.model.Transaction;
import bitwheeze.golos.goloslib.utilities.KeyFormatter;
import com.google.common.primitives.Bytes;
import one.block.eosiojava.enums.AlgorithmEmployed;
import one.block.eosiojava.error.utilities.EOSFormatterError;
import one.block.eosiojava.error.utilities.EosFormatterSignatureIsNotCanonicalError;
import one.block.eosiojava.error.utilities.PEMProcessorError;
import one.block.eosiojava.utilities.PEMProcessor;
import org.bitcoinj.core.Base58;
import org.bitcoinj.core.Sha256Hash;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import one.block.eosiojava.utilities.EOSFormatter;

@Service
public class SecurityUtils {

    @Value("${golos.chain_id}")
    String chainId;

    private static final int MAX_NOT_CANONICAL_RE_SIGN = 100;
    private static final int BIG_INTEGER_POSITIVE = 1;
    private static final int R_INDEX = 0;
    private static final int S_INDEX = 1;

    public static String getPublicKeyFromPrivateKey(String privKey) {
        try {
            String pemPrivKey = KeyFormatter.convertPrivateKeyToPEMFormat(privKey);
            PEMProcessor proc = new PEMProcessor(pemPrivKey);
            String pubKey = proc.extractEOSPublicKeyFromPrivateKey(true);
            return pubKey.replaceAll("^EOS", "GLS");
        } catch (EOSFormatterError | PEMProcessorError error) {
            throw new RuntimeException(error);
        }
    }

    public static String getPrivateKeyFromString(String seed) {
        var hash = Bytes.concat(new byte[] {(byte)0x80}, Sha256Hash.hash(seed.getBytes()));
        System.out.println("hash " + Hex.toHexString(hash).toLowerCase());
        var checksum = Arrays.copyOfRange(Sha256Hash.hash(Sha256Hash.hash(hash)), 0, 4);
        System.out.println("checksum " + Hex.toHexString(checksum).toLowerCase());
        return Base58.encode(Bytes.concat(hash,checksum));
    }

    /**
     * Sign transaction.
     * @param tr
     * @param serializedTransaction
     * @param keys
     * @return Signed transaction
     */
    public Transaction signTransaction(Transaction tr, String serializedTransaction, String[] keys) {
        byte[] message;

        // This is the hashed message which is signed.
        byte[] hashedMessage;

        message = Hex.decode(chainId + serializedTransaction);
        hashedMessage = Sha256Hash.hash(message);

        List<String> signatures = new ArrayList<>();
        try {
            for (String privKey : keys) {

                String pemPrivKey = KeyFormatter.convertPrivateKeyToPEMFormat(privKey);
                PEMProcessor keyProcessor = new PEMProcessor(pemPrivKey);
                String pubKey = keyProcessor.extractEOSPublicKeyFromPrivateKey(true).replaceFirst("EOS", "GLS");
                BigInteger privateKeyBI = new BigInteger(BIG_INTEGER_POSITIVE, keyProcessor.getKeyData());

                for (int i = 0; i < MAX_NOT_CANONICAL_RE_SIGN; i++) {
                    // Sign transaction
                    // Use default constructor to have signature generated with secureRandom, otherwise it would generate same signature for same key all the time
                    ECDSASigner signer = new ECDSASigner();

                    ECDomainParameters domainParameters;
                    try {
                        domainParameters = PEMProcessor.getCurveDomainParameters(AlgorithmEmployed.SECP256K1);
                    } catch (PEMProcessorError processorError) {
                        throw new RuntimeException("unknown algorithm " + AlgorithmEmployed.SECP256K1.name());
                    }

                    ECPrivateKeyParameters parameters = new ECPrivateKeyParameters(privateKeyBI, domainParameters);
                    signer.init(true, parameters);
                    BigInteger[] signatureComponents = signer.generateSignature(hashedMessage);

                    try {
                        String signature = KeyFormatter.convertRawRandSofSignatureToEOSFormat(
                                signatureComponents[R_INDEX].toString()
                                , signatureComponents[S_INDEX].toString()
                                , message
                                , KeyFormatter.convertEOSPublicKeyToPEMFormat(pubKey));
                        // Format Signature
                        signatures.add(signature);
                        break;
                    } catch (EOSFormatterError eosFormatterError) {
                        // In theory, Non-canonical error only happened with K1 key
                        if (eosFormatterError.getCause() instanceof EosFormatterSignatureIsNotCanonicalError) {
                            // Try to sign again until MAX_NOT_CANONICAL_RE_SIGN is reached or get a canonical signature
                            continue;
                        }

                        throw new RuntimeException("Was unable to sign!", eosFormatterError);
                    }
                }
            }
        } catch (EOSFormatterError | PEMProcessorError e) {
            throw new RuntimeException("Exception while signing", e);
        }

        tr.setSignatures(signatures.toArray(new String[signatures.size()]));
        return tr;
    }

    public byte[] getSerializedTransaction(Transaction tr) {
        //TODO: call api method
        return null;
    }
}
