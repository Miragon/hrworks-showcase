package io.miragon.timecync.adapter.out.clockify;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClockifyAdapterAutoConfiguration {

    @Bean
    ClockifyAdapter clockifyAdapter() {
        return new ClockifyAdapter();
    }
}