package bitwheeze.golos.goloslib;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "golos")
public class ApplicationProperties {
    String api;

}
