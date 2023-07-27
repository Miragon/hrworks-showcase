package io.miragon.timesync.adapter.out.hrworks;

import io.miragon.timesync.adapter.out.hrworks.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@EnableConfigurationProperties(HrWorksProperties.class)
@RequiredArgsConstructor
public class HrWorksAutoConfiguration {

    private final HrWorksProperties hrWorksProperties;

    @Bean
    HrWorksAdapter hrWorksAdapter() {
        return new HrWorksAdapter(hrWorksWebClient());
    }

    @Bean
    WebClient hrWorksWebClient() {
        return WebClient.builder()
                .baseUrl(hrWorksProperties.getBaseUrl())
                .filter(new JwtAuthenticationFilter(hrWorksProperties))
                .build();
    }
}