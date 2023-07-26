package io.miragon.timecync.adapter.in.miranum;

import io.miragon.timecync.application.port.in.synctimes.SyncTimesUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MiranumInAutoConfiguration {

    @Bean
    public MiranumWorkerAdapter miranumWorkerAdapter(final SyncTimesUseCase syncTimesUseCase) {
        return new MiranumWorkerAdapter(syncTimesUseCase);
    }
}