package io.miragon.timesync.application.port.out.aggregateTimeEntries;

public interface AggregateTimeEntriesPort {

    AggregateTimeEntriesResult aggregateTimeEntries(AggregateTimeEntriesCommand aggregateTimeEntriesCommand);
}