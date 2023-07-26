package io.miragon.timesync.application.service;

import io.miragon.timesync.application.port.in.synctimes.SyncTimesUseCase;
import io.miragon.timesync.application.port.out.HealthCheckPort;
import io.miragon.timesync.application.port.out.LoadWorkspacesPort;
import io.miragon.timesync.application.port.out.aggregateTimeEntries.AggregateTimeEntriesCommand;
import io.miragon.timesync.application.port.out.aggregateTimeEntries.AggregateTimeEntriesPort;
import io.miragon.timesync.application.port.out.loadusers.LoadUsersCommand;
import io.miragon.timesync.application.port.out.loadusers.LoadUsersPort;
import io.miragon.timesync.domain.User;
import io.miragon.timesync.domain.Workspace;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequiredArgsConstructor
public class SyncTimesService implements SyncTimesUseCase {

    private final LoadWorkspacesPort loadWorkspacesPort;
    private final LoadUsersPort loadUsersPort;
    private final AggregateTimeEntriesPort aggregateTimeEntriesPort;
    private final HealthCheckPort healthCheckPort;

    @Override
    public void syncTimes() {
        List<Workspace> workspaces =  loadWorkspacesPort.loadWorkspaces();
        Workspace workspace = workspaces.get(0);
        List<User> users = loadUsersPort.loadUsers(new LoadUsersCommand(workspace));
        var aggregateTimeEntriesCommand = new AggregateTimeEntriesCommand(
                workspace,
                users,
                LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0)
                .minus(2, ChronoUnit.MONTHS),
                LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0)
        );
        var timeEntries = aggregateTimeEntriesPort.aggregateTimeEntries(aggregateTimeEntriesCommand);
        healthCheckPort.healthCheck();
    }
}