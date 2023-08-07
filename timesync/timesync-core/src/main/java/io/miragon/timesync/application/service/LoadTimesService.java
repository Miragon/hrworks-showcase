package io.miragon.timesync.application.service;

import io.miragon.timesync.application.port.in.loadTimes.LoadTimesCommand;
import io.miragon.timesync.application.port.in.loadTimes.LoadTimesResult;
import io.miragon.timesync.application.port.in.loadTimes.LoadTimesUseCase;
import io.miragon.timesync.application.port.out.aggregateTimeEntries.AggregateTimeEntriesCommand;
import io.miragon.timesync.application.port.out.aggregateTimeEntries.AggregateTimeEntriesPort;
import io.miragon.timesync.application.port.out.aggregateTimeEntries.AggregateTimeEntriesResult;
import io.miragon.timesync.domain.User;
import io.miragon.timesync.domain.Workspace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.LocalTime;

@RequiredArgsConstructor
@Slf4j
public class LoadTimesService implements LoadTimesUseCase
{
    private final AggregateTimeEntriesPort aggregateTimeEntriesPort;

    private final Workspace workspace;

    private static final LocalDateTime firstOfCurrentMonth = LocalDateTime.now()
            .withDayOfMonth(1)
            .with(LocalTime.MIN);
    private static final LocalDateTime firstDayOfTwoMonthsAgo = LocalDateTime.now()
            .minusMonths(2)
            .withDayOfMonth(1)
            .with(LocalTime.MIN);

    @Override
    public LoadTimesResult loadTimes(LoadTimesCommand loadTimesCommand)
    {
        User user = loadTimesCommand.getUser();
        var aggregateTimeEntriesCommand =
                new AggregateTimeEntriesCommand(workspace, user, firstDayOfTwoMonthsAgo, firstOfCurrentMonth);
        log.info("[LOAD-TIMES] Aggregating time entries...");
        AggregateTimeEntriesResult timeEntries = aggregateTimeEntriesPort.aggregateTimeEntries(aggregateTimeEntriesCommand);

        return new LoadTimesResult(timeEntries.getAggregatedUserTimes());
    }
}
