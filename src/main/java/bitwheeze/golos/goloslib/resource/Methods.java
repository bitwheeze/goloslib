package bitwheeze.golos.goloslib.resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.PolyglotException;
import org.graalvm.polyglot.Value;
import org.springframework.stereotype.Component;

import bitwheeze.golos.goloslib.model.ApiMethod;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Methods {

    private final Map<String, Integer> methods = new HashMap<String, Integer>();

    public ApiMethod getApiMethod(String api, String method) {
        Integer count = methods.get(api + "." + method);
        return new ApiMethod(api, method, count);
    }

    @PostConstruct
    public void init() {
        Context context = Context.newBuilder("js").allowHostClassLookup(s -> true).allowHostAccess(HostAccess.ALL)
                .build();

        try (InputStream methodsjs = this.getClass().getClassLoader().getResourceAsStream("methods.js")) {
            context.eval("js", "var module = {exports: null};");
            context.eval("js", readFromInputStream(methodsjs));

            Value module = context.getBindings("js").getMember("module");
            Value methodsDef = module.getMember("exports");

            log.info("read modules {}", methodsDef);
            for (int i = 0; i < methodsDef.getArraySize(); i++) {
                Value methodDef = methodsDef.getArrayElement(i);

                String api = methodDef.getMember("api").asString();
                String method = methodDef.getMember("method").asString();
                Value parametersDef = methodDef.getMember("params");
                log.debug("api = {}, method {}, params = {}", api, method, parametersDef);
                int paraCount = 0;
                if(null != parametersDef) {
                    paraCount = (int) parametersDef.getArraySize();
                } else {
                    log.warn("api method {}.{} has no params ({})", api, method, methodDef);
                }
                methods.put(api + "." + method, paraCount);
            }
        } catch (PolyglotException e) {
            log.error("Location {}", e.getSourceLocation());
            log.error("unable to parse golos.min.js", e);
        } catch (IOException e) {
            log.error("unable to read golos.min.js", e);
        }

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
