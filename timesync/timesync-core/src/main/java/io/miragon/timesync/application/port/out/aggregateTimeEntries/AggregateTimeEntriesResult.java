package io.miragon.timesync.application.port.out.aggregateTimeEntries;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AggregateTimeEntriesResult {

    /**
     * Format: E-Mail -> {StartDateAndTime -> EndTimeAndDate}
     */
    private Map<String, Map<String, String>> aggregatedUserTimes;
}