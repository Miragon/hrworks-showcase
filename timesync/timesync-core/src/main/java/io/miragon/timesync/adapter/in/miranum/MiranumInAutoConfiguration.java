package io.miragon.timesync.adapter.in.miranum;

import io.miragon.timesync.application.port.in.loadTimes.LoadTimesUseCase;
import io.miragon.timesync.application.port.in.loadUsers.LoadUsersUseCase;
import io.miragon.timesync.application.port.in.synctimes.SyncTimesUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MiranumInAutoConfiguration {

    @Bean
    public MiranumWorkerAdapter miranumWorkerAdapter(
            final LoadUsersUseCase loadUsersUseCase,
            final LoadTimesUseCase loadTimesUseCase,
            final SyncTimesUseCase syncTimesUseCase)
    {
        return new MiranumWorkerAdapter(loadUsersUseCase, loadTimesUseCase, syncTimesUseCase);
    }
}
