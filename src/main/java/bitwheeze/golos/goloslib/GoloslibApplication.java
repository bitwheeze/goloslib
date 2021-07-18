package bitwheeze.golos.goloslib;

import java.math.BigDecimal;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.ObjectMapper;

import bitwheeze.golos.goloslib.model.Asset;
import bitwheeze.golos.goloslib.model.Transaction;
import bitwheeze.golos.goloslib.model.op.Transfer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class GoloslibApplication implements CommandLineRunner {

    @Autowired
    GolosApi api;
    
    @Autowired
    ObjectMapper mapper;
    
    @Autowired 
    TransactionBuilderFactory factory;
    
    @Value("${golos.test_key:5JUaDo9PXDU7grENDQMhfsXNKVvSHAPCvhYW3AaL9aBRx7hZTDt}")
    String test_key;
    
    public static void main(String[] args) {
        SpringApplication.run(GoloslibApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Testing golos connection...");
/*        
        String json = "{\"jsonrpc\":\"2.0\",\"result\":{\"id\":0,\"head_block_number\":49666765,\"head_block_id\":\"02f5dacddde84a8f564e9d7f4bee8a9cfacf4308\",\"time\":\"2021-07-15T19:30:30\",\"current_witness\":\"actual\",\"total_pow\":2421812,\"num_pow_witnesses\":172,\"virtual_supply\":\"275796592.424 GOLOS\",\"current_supply\":\"263731338.147 GOLOS\",\"confidential_supply\":\"0.000 GOLOS\",\"current_sbd_supply\":\"434349.154 GBG\",\"confidential_sbd_supply\":\"0.000 GBG\",\"total_vesting_fund_steem\":\"144419139.379 GOLOS\",\"total_vesting_shares\":\"414943703256.324249 GESTS\",\"accumulative_balance\":\"2575.815 GOLOS\",\"total_reward_fund_steem\":\"116799.381 GOLOS\",\"total_reward_shares2\":\"66181739363420240\",\"sbd_interest_rate\":0,\"sbd_print_rate\":2100,\"sbd_debt_percent\":437,\"average_block_size\":135,\"maximum_block_size\":65536,\"current_aslot\":49863010,\"recent_slots_filled\":\"340282366920938463463374607431768211455\",\"participation_count\":128,\"last_irreversible_block_num\":49666749,\"max_virtual_bandwidth\":\"5986734968066277376\",\"current_reserve_ratio\":20000,\"custom_ops_bandwidth_multiplier\":10,\"is_forced_min_price\":true,\"transit_block_num\":29585430,\"transit_witnesses\":\"76766b0000000000000000000000000078746172000000000000000000000000646d696c617368000000000000000000726f706f7800000000000000000000006c657800000000000000000000000000676f6c6f73636f72650000000000000076696b00000000000000000000000000737465657073686f740000000000000073746968692d696f0000000000000000676f6c6f73696f0000000000000000006c6164797a6172756c656d0000000000797564696e612d63617400000000000063726561746f720000000000000000006b756e6100000000000000000000000064656e69732d736b7269706e696b00007072696d7573000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000\",\"worker_requests\":[[\"91600047785731\",0]],\"accumulative_emission_per_day\":\"63676.800 GOLOS\"},\"id\":101}";
        
        log.info("to json = {}", mapper.writeValueAsString(new DynamicGlobalPropertiesResponse()));
        var resp = mapper.readValue(json, DynamicGlobalPropertiesResponse.class);
        log.info("from json {}", resp);
        log.info("current supply {} - {}", resp.getResult().getCurrentSupply().getValue(), resp.getResult().getCurrentSupply().getAssetName());
        DynamicGlobalPropertiesResponse props = api.getDynamicGlobalProperties();
        log.info("props = {}", props);
        BlockResponse block = api.getBlock(49693508);
        log.info("block = {}", block);
        OpsInBlockResponse opsInBlock = api.getOpsInBlock(49662837, false);
        log.info("opsInBlock = ");
        for(OperationHistoryRecord rec : opsInBlock.getResult()) {
            log.info("   op = {}", rec.getOp());
        }
*/
        /*
        String trString = "{\"ref_block_num\":49186,\"ref_block_prefix\":2378203595,\"expiration\":\"2021-07-17T20:27:45\",\"operations\":[[\"transfer\",{\"from\":\"bitwheeze\",\"to\":\"bitwheeze\",\"amount\":\"1.000 GOLOS\",\"memo\":\"цуацуа\"}]],\"extensions\":[],\"signatures\":[\"1f0821d1872cab51081724aaabe5b1987bbfb15c4637f22afb3678ae50ab24bc3808652543f552b6feed8f91d7932c720d014f9bb16107f44de80030651f87a552\"]}";
        var tr = mapper.readValue(trString, Transaction.class);
        log.info("tr = {}", tr);
        var response = api.broadcastTransaction(tr);
        //var response = mapper.writeValueAsString(tr);
        log.info("response = {}", response);
        */
        var builder = factory.getBuidler()
                .add(new Transfer("bitwheeze", "bitwheeze", new Asset(new BigDecimal("1.000"), "GOLOS"), "test java lib"));
        
        log.info("1");
        Transaction signedTr = builder.sign(new String [] {test_key});
        log.info("1");
        signedTr = builder.sign(new String [] {test_key});
        log.info("1");
        signedTr = builder.sign(new String [] {test_key});
        log.info("1");
        signedTr = builder.sign(new String [] {test_key});
     
        log.info("signed tr {}", signedTr);
        
        var response = api.broadcastTransaction(signedTr);   
        log.info("response = {}", response);
        
        var signer = Signature.getInstance("secp256k1");
        byte [] pkcs8EncodedBytes = Base64.getDecoder().decode(test_key.substring(1));
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pkcs8EncodedBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey privkey = kf.generatePrivate(keySpec);
        signer.initSign(privkey);
        signer.update("test".getBytes());
        log.info("signature "+ new String(signer.sign()));
    }

}
