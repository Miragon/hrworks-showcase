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
import io.miragon.timesync.domain.WorkingTimes;
import io.miragon.timesync.domain.Workspace;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class SyncTimesService implements SyncTimesUseCase {

    private final LoadWorkspacesPort loadWorkspacesPort;
    private final LoadUsersPort loadUsersPort;
    private final AggregateTimeEntriesPort aggregateTimeEntriesPort;
    private static final LocalDateTime firstOfCurrentMonth = LocalDateTime.now()
            .plusMonths(1)
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
        List<Workspace> workspaces = loadWorkspacesPort.loadWorkspaces();
        Workspace workspace = workspaces.get(0);
        List<User> users = loadUsersPort.loadUsers(new LoadUsersCommand(workspace));
        var aggregateTimeEntriesCommand =
                new AggregateTimeEntriesCommand(workspace, users, firstDayOfTwoMonthsAgo, firstOfCurrentMonth);
        var timeEntries = aggregateTimeEntriesPort.aggregateTimeEntries(aggregateTimeEntriesCommand);

        var employeesData = loadEmployeesDataPort.loadEmployeesData();

        List<WorkingTimes> workingTimes = new ArrayList<>();

        for (var data : employeesData) {
            var aggregatedUserTimes = timeEntries.getAggregatedUserTimes();
            if (aggregatedUserTimes.containsKey(data.getEmail())) {
                var entry = aggregatedUserTimes.get(data.getEmail());
                entry.forEach((beginDateAndTime, endDateAndTime) -> {
                    workingTimes.add(
                            new WorkingTimes(
                                    beginDateAndTime,
                                    endDateAndTime,
                                    data.getPersonnelNumber()
                            ));
                });
            }
        }

        sendWorkingtimesPort.sendWorkingTimes(new SendWorkingTimesCommand(workingTimes));
    }
}