package bitwheeze.golos.goloslib.resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.annotation.PostConstruct;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.PolyglotException;
import org.graalvm.polyglot.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import bitwheeze.golos.goloslib.model.Transaction;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Signature {
    
    @Autowired
    ObjectMapper mapper;

    private Context context;
    private Value stringify;
    private Value signTransaction;
    
    @PostConstruct
    public void init() {
        context = Context.newBuilder("js")
                .allowHostClassLookup(s -> true)
                .allowHostAccess(HostAccess.ALL)
                .build();
        
        try (InputStream golosjs = this.getClass().getClassLoader().getResourceAsStream("golos.min.js"); 
                InputStream crypto = this.getClass().getClassLoader().getResourceAsStream("crypto.js")) 
        {
            context.eval("js", readFromInputStream(crypto));
            context.eval("js", readFromInputStream(golosjs));
            context.eval("js", "function stringify(o) { return JSON.stringify(o); } ");
            signTransaction = context.getBindings("js").getMember("golos").getMember("auth").getMember("signTransaction");
            stringify = context.getBindings("js").getMember("stringify");

        } catch (PolyglotException e) {
            log.error("Location {}", e.getSourceLocation());
            log.error("unable to parse golos.min.js", e);
        } catch (IOException e) {
            log.error("unable to read golos.min.js", e);
        }
    }

    @SneakyThrows
    public Transaction signTransaction(Transaction tr, String[] keys) {
        String trJson = mapper.writeValueAsString(tr);
        String keysJson = mapper.writeValueAsString(keys);
        var trObj = context.eval("js", "(" + trJson + ")");
        var keysObj = context.eval("js", "(" + keysJson + ")");
        var signedTransaction = signTransaction.execute(trObj, keysObj);
        signedTransaction = stringify.execute(signedTransaction);
        return mapper.readValue(signedTransaction.asString(), Transaction.class);
    }
    
    private String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }    
}
