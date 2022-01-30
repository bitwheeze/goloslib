package bitwheeze.golos.goloslib;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.LoggingCodecSupport;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.util.Collections;

@Slf4j
@Configuration
public class GolosApiConfiguration {

    @Value("${golos.api:https://api-golos.blckchnd.com}")
    private String url;
    
    @Bean(name = "golos_api")
    public WebClient buildWebClient(ObjectMapper mapper) {
        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(clientDefaultCodecsConfigurer -> {
                    clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(mapper, MediaType.APPLICATION_JSON));
                    clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(mapper, MediaType.APPLICATION_JSON));
                })
                .build();
                
        exchangeStrategies
            .messageWriters().stream()
            .filter(LoggingCodecSupport.class::isInstance)
            .forEach(writer -> ((LoggingCodecSupport)writer).setEnableLoggingRequestDetails(true));
        
        log.info("using api url {}", url);
        return WebClient.builder()
                .baseUrl(url)
                //.defaultCookie("cookieKey", "cookieValue")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) 
                .defaultUriVariables(Collections.singletonMap("url", url))
                .exchangeStrategies(exchangeStrategies)
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create().wiretap(true)
                    ))
                .build();        
    }

    @Primary
    @Bean
    public ObjectMapper buildObjectMapper(Jackson2ObjectMapperBuilder builder) {
        var mapper = builder.build();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        return mapper;
    }
    
}
