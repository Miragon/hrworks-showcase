package io.miragon.timesync.application.port.out.aggregateTimeEntries;

import io.miragon.timesync.domain.User;
import io.miragon.timesync.domain.Workspace;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class AggregateTimeEntriesCommand {

    private Workspace workspace;

    private User user;

    private LocalDateTime fromDateTime;

    private LocalDateTime toDateTime;
}
