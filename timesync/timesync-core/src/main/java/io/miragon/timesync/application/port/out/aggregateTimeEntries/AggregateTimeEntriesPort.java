package io.miragon.timesync.application.port.out.aggregateTimeEntries;

import org.springframework.web.reactive.function.client.WebClientResponseException;

public interface AggregateTimeEntriesPort {

    AggregateTimeEntriesResult aggregateTimeEntries(AggregateTimeEntriesCommand aggregateTimeEntriesCommand) throws WebClientResponseException;
}
