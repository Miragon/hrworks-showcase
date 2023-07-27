package io.miragon.timesync;

import io.miragon.timesync.adapter.in.miranum.MiranumInAutoConfiguration;
import io.miragon.timesync.adapter.out.clockify.ClockifyAdapterAutoConfiguration;
import io.miragon.timesync.adapter.out.hrworks.HrWorksAutoConfiguration;
import io.miragon.timesync.application.port.in.synctimes.SyncTimesUseCase;
import io.miragon.timesync.application.port.out.aggregateTimeEntries.AggregateTimeEntriesPort;
import io.miragon.timesync.application.port.out.loademployeesdata.LoadEmployeesDataPort;
import io.miragon.timesync.application.port.out.loadusers.LoadUsersPort;
import io.miragon.timesync.application.port.out.loadworkspaces.LoadWorkspacesPort;
import io.miragon.timesync.application.port.out.sendworkingtimes.SendWorkingTimesPort;
import io.miragon.timesync.application.service.SyncTimesService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        MiranumInAutoConfiguration.class,
        ClockifyAdapterAutoConfiguration.class,
        HrWorksAutoConfiguration.class
})
public class TimeSyncAutoConfiguration {

    @Bean
    public SyncTimesUseCase syncTimesUseCase(final LoadWorkspacesPort loadWorkspacesPort,
                                             final LoadUsersPort loadUsersPort,
                                             final AggregateTimeEntriesPort aggregateTimeEntriesPort,
                                             final SendWorkingTimesPort sendWorkingTimesPort,
                                             final LoadEmployeesDataPort loadEmployeesDataPort) {
        return new SyncTimesService(loadWorkspacesPort, loadUsersPort, aggregateTimeEntriesPort, sendWorkingTimesPort, loadEmployeesDataPort);
    }
}