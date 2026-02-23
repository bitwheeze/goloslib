package bitwheeze.golos.goloslib.utilities;

import com.google.common.base.Preconditions;
import com.google.common.primitives.Bytes;
import one.block.eosiojava.enums.AlgorithmEmployed;
import one.block.eosiojava.error.utilities.*;
import one.block.eosiojava.utilities.PEMProcessor;
import org.bitcoinj.core.Base58;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Utils;
import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.asn1.x9.X9IntegerConverter;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.math.ec.ECAlgorithms;
import org.bouncycastle.math.ec.ECCurve.Fp;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.FixedPointUtil;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.Arrays;

public class KeyFormatter {
    private static final ECDomainParameters ecParamsR1;
    private static final ECDomainParameters ecParamsK1;
    private static final X9ECParameters CURVE_PARAMS_R1 = CustomNamedCurves.getByName("secp256r1");
    private static final X9ECParameters CURVE_PARAMS_K1 = CustomNamedCurves.getByName("secp256k1");
    private static final ECDomainParameters CURVE_R1;
    private static final BigInteger HALF_CURVE_ORDER_R1;
    private static final ECDomainParameters CURVE_K1;
    private static final BigInteger HALF_CURVE_ORDER_K1;

    public KeyFormatter() {
    }

    @NotNull
    public static String convertEOSPublicKeyToPEMFormat(@NotNull String publicKeyEOS) throws EOSFormatterError {
        String pemFormattedPublickKey;
        AlgorithmEmployed algorithmEmployed;
        String keyPrefix;

        if (!publicKeyEOS.toUpperCase().contains("GLS".toUpperCase())) {
            throw new EOSFormatterError("The GLS public key provided is invalid!");
        }

        algorithmEmployed = AlgorithmEmployed.SECP256K1;
        keyPrefix = "GLS";
        pemFormattedPublickKey = publicKeyEOS.replace(keyPrefix, "");

        byte[] base58DecodedPublicKey;
        try {
            base58DecodedPublicKey = decodePublicKey(pemFormattedPublickKey, keyPrefix);
        } catch (Exception var9) {
            throw new EOSFormatterError("An error occurred while Base58 decoding the GLS key!", var9);
        }

        pemFormattedPublickKey = Hex.toHexString(base58DecodedPublicKey);
        if (base58DecodedPublicKey[0] == 4) {
            try {
                pemFormattedPublickKey = Hex.toHexString(compressPublickey(Hex.decode(pemFormattedPublickKey), algorithmEmployed));
            } catch (Exception var8) {
                throw new EOSFormatterError(var8);
            }
        }

        pemFormattedPublickKey = "3036301006072a8648ce3d020106052b8104000a032200" + pemFormattedPublickKey;

        if (pemFormattedPublickKey.length() > 4) {
            int i = (pemFormattedPublickKey.length() - 4) / 2;
            String correctedLength = Integer.toHexString(i);
            pemFormattedPublickKey = pemFormattedPublickKey.substring(0, 2) + correctedLength + pemFormattedPublickKey.substring(4);

            try {
                pemFormattedPublickKey = derToPEM(Hex.decode(pemFormattedPublickKey), PEMObjectType.PUBLICKEY);
                return pemFormattedPublickKey;
            } catch (Exception var7) {
                throw new EOSFormatterError(var7);
            }
        } else {
            throw new EOSFormatterError("The GLS public key provided is invalid!");
        }
    }

    @NotNull
    public static String convertRawRandSofSignatureToEOSFormat(@NotNull String signatureR, String signatureS, @NotNull byte[] signableTransaction, @NotNull String publicKeyPEM) throws EOSFormatterError {
        String eosFormattedSignature = "";

        try {
            PEMProcessor publicKey = new PEMProcessor(publicKeyPEM);
            AlgorithmEmployed algorithmEmployed = publicKey.getAlgorithm();
            byte[] keyData = publicKey.getKeyData();
            BigInteger r = new BigInteger(signatureR);
            BigInteger s = new BigInteger(signatureS);
            s = checkAndHandleLowS(s, algorithmEmployed);
            int recoverId = getRecoveryId(r, s, Sha256Hash.of(signableTransaction), keyData, algorithmEmployed);
            if (recoverId < 0) {
                throw new IllegalStateException("Could not recover public key from Signature.");
            } else {
                recoverId += 31;
                byte headerByte = Integer.valueOf(recoverId).byteValue();
                byte[] decodedSignature = Bytes.concat(new byte[][]{{headerByte}, Utils.bigIntegerToBytes(r, 32), Utils.bigIntegerToBytes(s, 32)});
                if (!isCanonical(decodedSignature)) {
                    throw new EosFormatterSignatureIsNotCanonicalError("Input signature is not canonical.");
                }
                return Hex.toHexString(decodedSignature);
            }
        } catch (Exception var15) {
            throw new EOSFormatterError("An error occured formating the signature!", var15);
        }
    }

    @NotNull
    public static String convertPrivateKeyToPEMFormat(@NotNull String privateKey) throws EOSFormatterError {
        String pemFormattedPrivateKey = privateKey;
        AlgorithmEmployed algorithmEmployed = AlgorithmEmployed.SECP256K1;

        byte[] base58DecodedPrivateKey;
        try {
            base58DecodedPrivateKey = decodePrivateKey(pemFormattedPrivateKey, algorithmEmployed);
        } catch (Exception var7) {
            throw new EOSFormatterError("An error occurred while Base58 decoding the EOS key!", var7);
        }

        pemFormattedPrivateKey = Hex.toHexString(base58DecodedPrivateKey);
        switch(algorithmEmployed) {
            case SECP256R1:
                pemFormattedPrivateKey = "30770201010420" + pemFormattedPrivateKey + "A00A06082A8648CE3D030107";
                break;
            case SECP256K1:
                pemFormattedPrivateKey = "302E0201010420" + pemFormattedPrivateKey + "A00706052B8104000A";
                break;
            default:
                throw new EOSFormatterError("Unsupported algorithm!");
        }

        if (pemFormattedPrivateKey.length() > 4) {
            int i = (pemFormattedPrivateKey.length() - 4) / 2;
            String correctedLength = Integer.toHexString(i);
            pemFormattedPrivateKey = pemFormattedPrivateKey.substring(0, 2) + correctedLength + pemFormattedPrivateKey.substring(4);

            try {
                pemFormattedPrivateKey = derToPEM(Hex.decode(pemFormattedPrivateKey), PEMObjectType.PRIVATEKEY);
                return pemFormattedPrivateKey;
            } catch (DerToPemConversionError var6) {
                throw new EOSFormatterError(var6);
            }
        } else {
            throw new EOSFormatterError("The EOS private key provided is invalid!");
        }
    }

    @NotNull
    private static String derToPEM(@NotNull byte[] derEncodedByteArray, @NotNull PEMObjectType pemObjectType) throws DerToPemConversionError {
        StringBuilder pemForm = new StringBuilder();

        try {
            if (pemObjectType.equals(PEMObjectType.PRIVATEKEY)) {
                pemForm.append("-----BEGIN EC PRIVATE KEY-----");
            } else {
                if (!pemObjectType.equals(PEMObjectType.PUBLICKEY)) {
                    throw new DerToPemConversionError("Error converting DER encoded key to PEM format!");
                }

                pemForm.append("-----BEGIN PUBLIC KEY-----");
            }

            pemForm.append("\n");
            String base64EncodedByteArray = new String(Base64.encode(derEncodedByteArray));
            pemForm.append(base64EncodedByteArray);
            pemForm.append("\n");
            if (pemObjectType.equals(PEMObjectType.PRIVATEKEY)) {
                pemForm.append("-----END EC PRIVATE KEY-----");
            } else {
                if (!pemObjectType.equals(PEMObjectType.PUBLICKEY)) {
                    throw new DerToPemConversionError("Error converting DER encoded key to PEM format!");
                }

                pemForm.append("-----END PUBLIC KEY-----");
            }
        } catch (Exception var4) {
            throw new DerToPemConversionError("Error converting DER encoded key to PEM format!", var4);
        }

        return pemForm.toString();
    }

    @NotNull
    private static byte[] decodePrivateKey(@NotNull String strKey, AlgorithmEmployed keyType) throws Base58ManipulationError {
        if (strKey.isEmpty()) {
            throw new IllegalArgumentException("Input key to decode can't be empty!");
        } else {
            try {
                byte[] base58Decoded = Base58.decode(strKey);
                byte[] firstCheckSum = Arrays.copyOfRange(base58Decoded, base58Decoded.length - 4, base58Decoded.length);
                byte[] decodedKey = Arrays.copyOfRange(base58Decoded, 0, base58Decoded.length - 4);
                switch(keyType) {
                    case SECP256R1:
                        byte[] secp256r1Suffix = "R1".getBytes();
                        if (invalidRipeMD160CheckSum(decodedKey, firstCheckSum, secp256r1Suffix)) {
                            throw new IllegalArgumentException("Input key has invalid checksum!");
                        }
                        break;
                    case SECP256K1:
                        if (invalidSha256x2CheckSum(decodedKey, firstCheckSum)) {
                            throw new IllegalArgumentException("Input key has invalid checksum!");
                        }
                        break;
                    case PRIME256V1:
                        byte[] prime256v1Suffix = "R1".getBytes();
                        if (invalidRipeMD160CheckSum(decodedKey, firstCheckSum, prime256v1Suffix)) {
                            throw new IllegalArgumentException("Input key has invalid checksum!");
                        }
                        break;
                    default:
                        throw new Base58ManipulationError("Unsupported algorithm!");
                }

                if (decodedKey.length > 32 && keyType != AlgorithmEmployed.SECP256R1) {
                    decodedKey = Arrays.copyOfRange(decodedKey, 1, decodedKey.length);
                    if (decodedKey.length > 32 && decodedKey[32] == Integer.valueOf(1).byteValue()) {
                        decodedKey = Arrays.copyOfRange(decodedKey, 0, decodedKey.length - 1);
                    }
                }

                return decodedKey;
            } catch (Exception var7) {
                throw new Base58ManipulationError("An error occurred while Base58 decoding the EOS key!", var7);
            }
        }
    }


    @NotNull
    public static byte[] decodePublicKey(@NotNull String strKey, String keyPrefix) throws Base58ManipulationError {
        if (strKey.isEmpty()) {
            throw new IllegalArgumentException("Input key to decode can't be empty.");
        } else {
            Object var2 = null;

            try {
                byte[] base58Decoded = Base58.decode(strKey);
                byte[] firstCheckSum = Arrays.copyOfRange(base58Decoded, base58Decoded.length - 4, base58Decoded.length);
                byte[] decodedKey = Arrays.copyOfRange(base58Decoded, 0, base58Decoded.length - 4);
                byte var6 = -1;
                switch(keyPrefix.hashCode()) {
                    case 68841:
                        if (keyPrefix.equals("EOS")) {
                            var6 = 2;
                        }
                        break;
                    case 483184503:
                        if (keyPrefix.equals("PUB_K1_")) {
                            var6 = 1;
                        }
                        break;
                    case 483191230:
                        if (keyPrefix.equals("PUB_R1_")) {
                            var6 = 0;
                        }
                }

                switch(var6) {
                    case 0:
                        if (invalidRipeMD160CheckSum(decodedKey, firstCheckSum, "R1".getBytes())) {
                            throw new IllegalArgumentException("Input key has invalid checksum!");
                        }
                        break;
                    case 1:
                        if (invalidRipeMD160CheckSum(decodedKey, firstCheckSum, "K1".getBytes())) {
                            throw new IllegalArgumentException("Input key has invalid checksum!");
                        }
                        break;
                    case 2:
                        if (invalidRipeMD160CheckSum(decodedKey, firstCheckSum, "".getBytes())) {
                            throw new IllegalArgumentException("Input key has invalid checksum!");
                        }
                }

                return decodedKey;
            } catch (Exception var7) {
                throw new Base58ManipulationError("An error occurred while Base58 decoding the EOS key!", var7);
            }
        }
    }

    private static boolean invalidRipeMD160CheckSum(@NotNull byte[] inputKey, @NotNull byte[] checkSumToValidate, @NotNull byte[] keyTypeByteArray) {
        if (inputKey.length != 0 && checkSumToValidate.length != 0) {
            byte[] keyWithType = Bytes.concat(new byte[][]{inputKey, keyTypeByteArray});
            byte[] digestRIPEMD160 = digestRIPEMD160(keyWithType);
            byte[] checkSumFromInputKey = Arrays.copyOfRange(digestRIPEMD160, 0, 4);
            return !Arrays.equals(checkSumToValidate, checkSumFromInputKey);
        } else {
            throw new IllegalArgumentException("Input key, checksum and key type to validate can't be empty!");
        }
    }

    private static boolean invalidSha256x2CheckSum(@NotNull byte[] inputKey, @NotNull byte[] checkSumToValidate) {
        if (inputKey.length != 0 && checkSumToValidate.length != 0) {
            byte[] sha256x2 = Sha256Hash.hashTwice(inputKey);
            byte[] checkSumFromInputKey = Arrays.copyOfRange(sha256x2, 0, 4);
            return !Arrays.equals(checkSumToValidate, checkSumFromInputKey);
        } else {
            throw new IllegalArgumentException("Input key, checksum and key type to validate can't be empty!");
        }
    }

    @NotNull
    private static byte[] digestRIPEMD160(@NotNull byte[] input) {
        RIPEMD160Digest digest = new RIPEMD160Digest();
        byte[] output = new byte[digest.getDigestSize()];
        digest.update(input, 0, input.length);
        digest.doFinal(output, 0);
        return output;
    }

    @NotNull
    private static byte[] extractCheckSumRIPEMD160(@NotNull byte[] pemKey, byte[] keyTypeByteArray) {
        if (keyTypeByteArray != null) {
            pemKey = Bytes.concat(new byte[][]{pemKey, keyTypeByteArray});
        }

        byte[] ripemd160Digest = digestRIPEMD160(pemKey);
        return Arrays.copyOfRange(ripemd160Digest, 0, 4);
    }

    @NotNull
    private static byte[] compressPublickey(byte[] compressedPublicKey, AlgorithmEmployed algorithmEmployed) throws EOSFormatterError {
        try {
            ECParameterSpec parameterSpec = ECNamedCurveTable.getParameterSpec(algorithmEmployed.getString());
            ECPoint ecPoint = parameterSpec.getCurve().decodePoint(compressedPublicKey);
            byte[] x = ecPoint.getXCoord().getEncoded();
            byte[] y = ecPoint.getYCoord().getEncoded();
            BigInteger bigIntegerY = new BigInteger(Hex.toHexString(y), 16);
            BigInteger bigIntegerTwo = BigInteger.valueOf(2L);
            BigInteger remainder = bigIntegerY.mod(bigIntegerTwo);
            byte compressionPrefix;
            if (remainder.equals(BigInteger.ZERO)) {
                compressionPrefix = 2;
            } else {
                compressionPrefix = 3;
            }

            return Bytes.concat(new byte[][]{{compressionPrefix}, x});
        } catch (Exception var10) {
            throw new EOSFormatterError("Problem compressing public key!", var10);
        }
    }

    private static BigInteger checkAndHandleLowS(BigInteger s, AlgorithmEmployed keyType) throws LowSVerificationError {
        if (!isLowS(s, keyType)) {
            switch(keyType) {
                case SECP256R1:
                    return CURVE_R1.getN().subtract(s);
                default:
                    return CURVE_K1.getN().subtract(s);
            }
        } else {
            return s;
        }
    }

    private static boolean isLowS(BigInteger s, AlgorithmEmployed keyType) throws LowSVerificationError {
        int compareResult;
        switch(keyType) {
            case SECP256R1:
                compareResult = s.compareTo(HALF_CURVE_ORDER_R1);
                break;
            case SECP256K1:
                compareResult = s.compareTo(HALF_CURVE_ORDER_K1);
                break;
            default:
                throw new LowSVerificationError("Unsupported algorithm!");
        }

        return compareResult == 0 || compareResult == -1;
    }

    private static boolean isCanonical(byte[] signature) {
        return (signature[1] & Integer.valueOf(128).byteValue()) == Integer.valueOf(0).byteValue() && (signature[1] != Integer.valueOf(0).byteValue() || (signature[2] & Integer.valueOf(128).byteValue()) != Integer.valueOf(0).byteValue()) && (signature[33] & Integer.valueOf(128).byteValue()) == Integer.valueOf(0).byteValue() && (signature[33] != Integer.valueOf(0).byteValue() || (signature[34] & Integer.valueOf(128).byteValue()) != Integer.valueOf(0).byteValue());
    }

    private static int getRecoveryId(BigInteger r, BigInteger s, Sha256Hash sha256HashMessage, byte[] publicKey, AlgorithmEmployed keyType) {
        for(int i = 0; i < 4; ++i) {
            byte[] recoveredPublicKey = recoverPublicKeyFromSignature(i, r, s, sha256HashMessage, true, keyType);
            if (Arrays.equals(publicKey, recoveredPublicKey)) {
                return i;
            }
        }

        return -1;
    }

    private static byte[] recoverPublicKeyFromSignature(int recId, BigInteger r, BigInteger s, @NotNull Sha256Hash message, boolean compressed, AlgorithmEmployed keyType) {
        Preconditions.checkArgument(recId >= 0, "recId must be positive");
        Preconditions.checkArgument(r.signum() >= 0, "r must be positive");
        Preconditions.checkArgument(s.signum() >= 0, "s must be positive");
        BigInteger n;
        ECPoint g;
        Fp curve;
        switch(keyType) {
            case SECP256R1:
                n = ecParamsR1.getN();
                g = ecParamsR1.getG();
                curve = (Fp)ecParamsR1.getCurve();
                break;
            default:
                n = ecParamsK1.getN();
                g = ecParamsK1.getG();
                curve = (Fp)ecParamsK1.getCurve();
        }

        BigInteger i = BigInteger.valueOf((long)recId / 2L);
        BigInteger x = r.add(i.multiply(n));
        BigInteger prime = curve.getQ();
        if (x.compareTo(prime) >= 0) {
            return null;
        } else {
            ECPoint R = decompressKey(x, (recId & 1) == 1, keyType);
            if (!R.multiply(n).isInfinity()) {
                return null;
            } else {
                BigInteger e = message.toBigInteger();
                BigInteger eInv = BigInteger.ZERO.subtract(e).mod(n);
                BigInteger rInv = r.modInverse(n);
                BigInteger srInv = rInv.multiply(s).mod(n);
                BigInteger eInvrInv = rInv.multiply(eInv).mod(n);
                ECPoint q = ECAlgorithms.sumOfTwoMultiplies(g, eInvrInv, R, srInv);
                return q.getEncoded(compressed);
            }
        }
    }

    private static ECPoint decompressKey(BigInteger xBN, boolean yBit, AlgorithmEmployed keyType) {
        Fp curve;
        switch(keyType) {
            case SECP256R1:
                curve = (Fp)ecParamsR1.getCurve();
                break;
            default:
                curve = (Fp)ecParamsK1.getCurve();
        }

        X9IntegerConverter x9 = new X9IntegerConverter();
        byte[] compEnc = x9.integerToBytes(xBN, 1 + x9.getByteLength(curve));
        compEnc[0] = (byte)(yBit ? 3 : 2);
        return curve.decodePoint(compEnc);
    }

    static {
        X9ECParameters paramsR1 = SECNamedCurves.getByName("secp256r1");
        ecParamsR1 = new ECDomainParameters(paramsR1.getCurve(), paramsR1.getG(), paramsR1.getN(), paramsR1.getH());
        X9ECParameters paramsK1 = SECNamedCurves.getByName("secp256k1");
        ecParamsK1 = new ECDomainParameters(paramsK1.getCurve(), paramsK1.getG(), paramsK1.getN(), paramsK1.getH());
        FixedPointUtil.precompute(CURVE_PARAMS_R1.getG());
        CURVE_R1 = new ECDomainParameters(CURVE_PARAMS_R1.getCurve(), CURVE_PARAMS_R1.getG(), CURVE_PARAMS_R1.getN(), CURVE_PARAMS_R1.getH());
        HALF_CURVE_ORDER_R1 = CURVE_PARAMS_R1.getN().shiftRight(1);
        CURVE_K1 = new ECDomainParameters(CURVE_PARAMS_K1.getCurve(), CURVE_PARAMS_K1.getG(), CURVE_PARAMS_K1.getN(), CURVE_PARAMS_K1.getH());
        HALF_CURVE_ORDER_K1 = CURVE_PARAMS_K1.getN().shiftRight(1);
    }

    private static enum PEMObjectType {
        PUBLICKEY("PUBLIC KEY"),
        PRIVATEKEY("PRIVATE KEY"),
        SIGNATURE("SIGNATURE");

        private String value;

        private PEMObjectType(String value) {
            this.value = value;
        }

        public String getString() {
            return this.value;
        }
    }
}
