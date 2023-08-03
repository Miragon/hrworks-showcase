package io.miragon.timesync.application.service;

import io.miragon.timesync.application.port.in.synctimes.LoadTimeCommand;
import io.miragon.timesync.application.port.in.synctimes.SyncTimesCommand;
import io.miragon.timesync.application.port.in.synctimes.SyncTimesUseCase;
import io.miragon.timesync.application.port.out.aggregateTimeEntries.AggregateTimeEntriesCommand;
import io.miragon.timesync.application.port.out.aggregateTimeEntries.AggregateTimeEntriesPort;
import io.miragon.timesync.application.port.out.aggregateTimeEntries.AggregateTimeEntriesResult;
import io.miragon.timesync.application.port.out.loademployeesdata.LoadEmployeesDataPort;
import io.miragon.timesync.application.port.out.loadusers.LoadUsersCommand;
import io.miragon.timesync.application.port.out.loadusers.LoadUsersPort;
import io.miragon.timesync.application.port.out.loadworkspaces.LoadWorkspacesPort;
import io.miragon.timesync.application.port.out.sendworkingtimes.SendWorkingTimesCommand;
import io.miragon.timesync.application.port.out.sendworkingtimes.SendWorkingTimesPort;
import io.miragon.timesync.domain.User;
import io.miragon.timesync.domain.UserResponse;
import io.miragon.timesync.domain.Workspace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Slf4j
public
class SyncTimesService implements SyncTimesUseCase {

    private final LoadWorkspacesPort loadWorkspacesPort;
    private final LoadUsersPort loadUsersPort;
    private final AggregateTimeEntriesPort aggregateTimeEntriesPort;
    private static final LocalDateTime firstOfCurrentMonth = LocalDateTime.now()
            .withDayOfMonth(4)
            .with(LocalTime.MIN);
    private static final LocalDateTime firstDayOfTwoMonthsAgo = LocalDateTime.now()
            .minusMonths(2)
            .withDayOfMonth(1)
            .with(LocalTime.MIN);

    private final SendWorkingTimesPort sendWorkingtimesPort;
    private final LoadEmployeesDataPort loadEmployeesDataPort;

    private Workspace workspace;

    @Override
    public UserResponse loadUsers() {
        log.info("[LOAD-USERS] Syncing times...");
        log.info("[LOAD-USERS] Loading workspaces...");
        List<Workspace> workspaces = loadWorkspacesPort.loadWorkspaces();
        this.workspace = workspaces.get(0);
        log.info("[LOAD-USERS] Loading users...");
        List<User> users = loadUsersPort.loadUsers(new LoadUsersCommand(workspace));
        return new UserResponse(users);
    }

    @Override
    public AggregateTimeEntriesResult loadTime(LoadTimeCommand loadTimeCommand) {
        User user = loadTimeCommand.getUser();
        var aggregateTimeEntriesCommand =
                new AggregateTimeEntriesCommand(this.workspace, user, firstDayOfTwoMonthsAgo, firstOfCurrentMonth);
        log.info("[LOAD-TIMES] Aggregating time entries...");
        return aggregateTimeEntriesPort.aggregateTimeEntries(aggregateTimeEntriesCommand);
    }

    @Override
    public void syncTimes(SyncTimesCommand syncTimesCommand) {
        User user = syncTimesCommand.getUser();
        log.info("[SYNC-TIMES] Loading employees data...");
        var employeesData = loadEmployeesDataPort.loadEmployeesData();

        log.info("[SYNC-TIMES] Sending working times...");
        for (var data : employeesData.getPersons()) {
            if (Objects.equals(user.getEmail(), data.getEmail())) {
                var aggregatedUserTimes = syncTimesCommand.getTimeEntries();
                aggregatedUserTimes.forEach((beginDateAndTime, endDateAndTime) -> sendWorkingtimesPort.sendWorkingTimes(
                        new SendWorkingTimesCommand(
                                beginDateAndTime,
                                endDateAndTime,
                                data.getPersonnelNumber())));
            }
        }
        log.info("[SYNC-TIMES] Syncing times finished");
    }

}
