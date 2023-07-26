package io.miragon.timecync;

import io.miragon.timecync.adapter.in.miranum.MiranumInAutoConfiguration;
import io.miragon.timecync.adapter.out.clockify.ClockifyAdapterAutoConfiguration;
import io.miragon.timecync.application.port.in.synctimes.SyncTimesUseCase;
import io.miragon.timecync.application.port.out.LoadWorkspacesPort;
import io.miragon.timecync.application.port.out.aggregateTimeEntries.AggregateTimeEntriesPort;
import io.miragon.timecync.application.port.out.loadusers.LoadUsersPort;
import io.miragon.timecync.application.service.SyncTimesService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        MiranumInAutoConfiguration.class,
        ClockifyAdapterAutoConfiguration.class
})
public class TimeSyncAutoConfiguration {

    @Bean
    public SyncTimesUseCase syncTimesUseCase(final LoadWorkspacesPort loadWorkspacesPort,
                                             final LoadUsersPort loadUsersPort,
                                             final AggregateTimeEntriesPort aggregateTimeEntriesPort) {
        return new SyncTimesService(loadWorkspacesPort, loadUsersPort, aggregateTimeEntriesPort);
    }
}