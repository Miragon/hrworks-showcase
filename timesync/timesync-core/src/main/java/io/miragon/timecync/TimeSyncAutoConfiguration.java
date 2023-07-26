package io.miragon.timecync;

import io.miragon.timecync.adapter.in.miranum.MiranumInAutoConfiguration;
import io.miragon.timecync.application.port.in.dosomething.DoSomethingUseCase;
import io.miragon.timecync.application.service.DoSomethingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        MiranumInAutoConfiguration.class,
})
public class TimeSyncAutoConfiguration {

    @Bean
    public DoSomethingUseCase doSomethingUseCase() {
        return new DoSomethingService();
    }

}