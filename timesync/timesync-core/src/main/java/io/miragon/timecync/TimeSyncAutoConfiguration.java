package io.miragon.timecync;

import io.miragon.timecync.adapter.in.miranum.MiranumInAutoConfiguration;
import io.miragon.timecync.application.port.in.synctimes.SyncTimesUseCase;
import io.miragon.timecync.application.service.SyncTimesService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        MiranumInAutoConfiguration.class,
})
public class TimeSyncAutoConfiguration {

    @Bean
    public SyncTimesUseCase syncTimesUseCase() {
        return new SyncTimesService();
    }
}