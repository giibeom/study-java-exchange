package woowaapplication.pair.exchange.common.config;

import java.time.Duration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateSetting {

    @Bean
    public RestTemplate generalRestTemplate() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        restTemplateBuilder.errorHandler(new RestApiErrorHandler());
        restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(1L));

        return restTemplateBuilder.build();
    }
}
