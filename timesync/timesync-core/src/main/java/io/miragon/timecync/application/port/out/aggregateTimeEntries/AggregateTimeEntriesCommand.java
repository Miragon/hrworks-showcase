package io.miragon.timecync.application.port.out.aggregateTimeEntries;

import io.miragon.timecync.domain.User;
import io.miragon.timecync.domain.Workspace;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class AggregateTimeEntriesCommand {

    private Workspace workspace;

    private List<User> users;

    private LocalDateTime fromDateTime;

    private LocalDateTime toDateTime;
}