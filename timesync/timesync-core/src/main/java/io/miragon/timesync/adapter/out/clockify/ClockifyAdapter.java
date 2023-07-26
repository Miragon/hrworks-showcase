package io.miragon.timesync.adapter.out.clockify;

import io.miragon.timesync.application.port.out.LoadWorkspacesPort;
import io.miragon.timesync.application.port.out.aggregateTimeEntries.AggregateTimeEntriesCommand;
import io.miragon.timesync.application.port.out.aggregateTimeEntries.AggregateTimeEntriesPort;
import io.miragon.timesync.application.port.out.loadusers.LoadUsersCommand;
import io.miragon.timesync.application.port.out.loadusers.LoadUsersPort;
import io.miragon.timesync.domain.User;
import io.miragon.timesync.domain.Workspace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ClockifyAdapter implements LoadUsersPort, LoadWorkspacesPort, AggregateTimeEntriesPort {

    private final WebClient webClient;

    @Override
    public List<Workspace> loadWorkspaces() {
        return webClient.get()
                .uri("/workspaces")
                .retrieve()
                .bodyToFlux(Workspace.class)
                .collectList()
                .block();
    }

    @Override
    public Map<String, Map<String, Duration>> aggregateTimeEntries(AggregateTimeEntriesCommand aggregateTimeEntriesCommand) {
        return null;
    }

    @Override
    public List<User> loadUsers(LoadUsersCommand loadUserCommand) {
        return null;
    }
}