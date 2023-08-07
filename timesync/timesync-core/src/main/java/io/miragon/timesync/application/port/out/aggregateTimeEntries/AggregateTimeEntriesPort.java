package io.miragon.timesync.application.port.out.aggregateTimeEntries;

import org.springframework.web.reactive.function.client.WebClientResponseException;

public interface AggregateTimeEntriesPort {

    /**
     * Get the working times of one employee.
     * @param aggregateTimeEntriesCommand Information to identify the employee and time frame.
     * @return Working hours recorded by the employee in the given period of time.
     */
    AggregateTimeEntriesResult aggregateTimeEntries(AggregateTimeEntriesCommand aggregateTimeEntriesCommand);
}
