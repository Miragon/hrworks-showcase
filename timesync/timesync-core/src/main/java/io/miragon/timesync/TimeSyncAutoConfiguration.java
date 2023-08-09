package io.miragon.timesync;

import io.miragon.timesync.adapter.in.miranum.MiranumInAutoConfiguration;
import io.miragon.timesync.adapter.out.clockify.ClockifyAdapterAutoConfiguration;
import io.miragon.timesync.adapter.out.hrworks.HrWorksAutoConfiguration;
import io.miragon.timesync.application.port.in.loadTimes.LoadTimesUseCase;
import io.miragon.timesync.application.port.in.loadUsers.LoadUsersUseCase;
import io.miragon.timesync.application.port.in.synctimes.SyncTimesUseCase;
import io.miragon.timesync.application.port.out.aggregateTimeEntries.AggregateTimeEntriesPort;
import io.miragon.timesync.application.port.out.loademployeesdata.LoadEmployeesDataPort;
import io.miragon.timesync.application.port.out.loadusers.LoadUsersPort;
import io.miragon.timesync.application.port.out.loadworkspaces.LoadWorkspacesPort;
import io.miragon.timesync.application.port.out.sendworkingtimes.SendWorkingTimesPort;
import io.miragon.timesync.application.service.LoadTimesService;
import io.miragon.timesync.application.service.LoadUsersService;
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
    public LoadUsersUseCase loadUsersUseCase(final LoadWorkspacesPort loadWorkspacesPort,
                                             final LoadUsersPort loadUsersPort,
                                             final LoadEmployeesDataPort loadEmployeesDataPort)
    {
        return new LoadUsersService(loadWorkspacesPort, loadUsersPort, loadEmployeesDataPort);
    }

    @Bean
    public LoadTimesUseCase loadTimesUseCase(final AggregateTimeEntriesPort aggregateTimeEntriesPort)
    {
        return new LoadTimesService(aggregateTimeEntriesPort);
    }


    @Bean
    public SyncTimesUseCase syncTimesUseCase(final SendWorkingTimesPort sendWorkingTimesPort)
    {
        return new SyncTimesService(sendWorkingTimesPort);
    }
}
