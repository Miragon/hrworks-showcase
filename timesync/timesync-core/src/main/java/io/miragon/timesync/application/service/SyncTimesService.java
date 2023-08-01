package io.miragon.timesync.application.service;

import io.miragon.timesync.application.port.in.synctimes.SyncTimesUseCase;
import io.miragon.timesync.application.port.out.aggregateTimeEntries.AggregateTimeEntriesCommand;
import io.miragon.timesync.application.port.out.aggregateTimeEntries.AggregateTimeEntriesPort;
import io.miragon.timesync.application.port.out.loademployeesdata.LoadEmployeesDataPort;
import io.miragon.timesync.application.port.out.loadusers.LoadUsersCommand;
import io.miragon.timesync.application.port.out.loadusers.LoadUsersPort;
import io.miragon.timesync.application.port.out.loadworkspaces.LoadWorkspacesPort;
import io.miragon.timesync.application.port.out.sendworkingtimes.SendWorkingTimesCommand;
import io.miragon.timesync.application.port.out.sendworkingtimes.SendWorkingTimesPort;
import io.miragon.timesync.domain.User;
import io.miragon.timesync.domain.Workspace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public
class SyncTimesService implements SyncTimesUseCase {

    private final LoadWorkspacesPort loadWorkspacesPort;
    private final LoadUsersPort loadUsersPort;
    private final AggregateTimeEntriesPort aggregateTimeEntriesPort;
    private static final LocalDateTime firstOfCurrentMonth = LocalDateTime.now()
            .withDayOfMonth(1)
            .with(LocalTime.MIN);
    private static final LocalDateTime firstDayOfTwoMonthsAgo = LocalDateTime.now()
            .minusMonths(2)
            .withDayOfMonth(1)
            .with(LocalTime.MIN);

    private final SendWorkingTimesPort sendWorkingtimesPort;
    private final LoadEmployeesDataPort loadEmployeesDataPort;

    @Override
    public void syncTimes() {
        log.info("[SYNC-TIMES] Syncing times...");
        log.info("[SYNC-TIMES] Loading workspaces...");
        List<Workspace> workspaces = loadWorkspacesPort.loadWorkspaces();
        Workspace workspace = workspaces.get(0);
        log.info("[SYNC-TIMES] Loading users...");
        List<User> users = loadUsersPort.loadUsers(new LoadUsersCommand(workspace));
        var aggregateTimeEntriesCommand =
                new AggregateTimeEntriesCommand(workspace, users, firstDayOfTwoMonthsAgo, firstOfCurrentMonth);
        log.info("[SYNC-TIMES] Aggregating time entries...");
        var timeEntries = aggregateTimeEntriesPort.aggregateTimeEntries(aggregateTimeEntriesCommand);

        log.info("[SYNC-TIMES] Loading employees data...");
        var employeesData = loadEmployeesDataPort.loadEmployeesData();

        log.info("[SYNC-TIMES] Sending working times...");
        for (var data : employeesData.getPersons()) {
            var aggregatedUserTimes = timeEntries.getAggregatedUserTimes();
            if (aggregatedUserTimes.containsKey(data.getEmail())) {
                var entry = aggregatedUserTimes.get(data.getEmail());
                entry.forEach((beginDateAndTime, endDateAndTime) -> sendWorkingtimesPort.sendWorkingTimes(
                        new SendWorkingTimesCommand(
                                beginDateAndTime,
                                endDateAndTime,
                                data.getPersonnelNumber())));
            }
        }
        log.info("[SYNC-TIMES] Syncing times finished");
    }
}