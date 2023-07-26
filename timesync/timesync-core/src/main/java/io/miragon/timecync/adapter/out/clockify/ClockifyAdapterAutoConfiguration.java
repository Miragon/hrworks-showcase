package io.miragon.timecync.adapter.out.clockify;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ClockifyProperties.class)
public class ClockifyAdapterAutoConfiguration {

    @Bean
    ClockifyAdapter clockifyAdapter() {
        return new ClockifyAdapter();
    }
}