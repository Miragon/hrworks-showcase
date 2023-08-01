package io.miragon.timesync.adapter.in.miranum;

import io.miragon.timesync.application.port.in.synctimes.SyncTimesUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MiranumInAutoConfiguration {

    @Bean
    public MiranumWorkerAdapter miranumWorkerAdapter(final SyncTimesUseCase syncTimesUseCase) {
        return new MiranumWorkerAdapter(syncTimesUseCase);
    }
}