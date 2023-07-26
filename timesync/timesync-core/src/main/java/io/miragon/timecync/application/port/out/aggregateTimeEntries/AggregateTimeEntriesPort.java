package io.miragon.timecync.application.port.out.aggregateTimeEntries;

import java.time.Duration;
import java.util.Map;

public interface AggregateTimeEntriesPort {

    Map<String, Map<String, Duration>> aggregateTimeEntries(AggregateTimeEntriesCommand aggregateTimeEntriesCommand);
}