package io.miragon.timesync.adapter.out.clockify;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableConfigurationProperties(ClockifyProperties.class)
@RequiredArgsConstructor
public class ClockifyAdapterAutoConfiguration {

    private final ClockifyProperties clockifyProperties;

    @Bean
    ClockifyAdapter clockifyAdapter() {
        return new ClockifyAdapter(clockifyWebClient());
    }

    @Bean
    WebClient clockifyWebClient() {
        return WebClient.builder()
                .baseUrl(clockifyProperties.getBaseUrl())
                .defaultHeader("x-api-key", clockifyProperties.getApiKey())
                .build();
    }
}