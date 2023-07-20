package bitwheeze.golos.goloslib;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "golos")
public class ApplicationProperties {
    String api;

}
