package io.miragon.timesync.adapter.out.clockify;

import io.miragon.timesync.application.port.out.aggregateTimeEntries.AggregateTimeEntriesCommand;
import io.miragon.timesync.application.port.out.aggregateTimeEntries.AggregateTimeEntriesPort;
import io.miragon.timesync.application.port.out.aggregateTimeEntries.AggregateTimeEntriesResult;
import io.miragon.timesync.application.port.out.loadusers.LoadUsersCommand;
import io.miragon.timesync.application.port.out.loadusers.LoadUsersPort;
import io.miragon.timesync.application.port.out.loadworkspaces.LoadWorkspacesPort;
import io.miragon.timesync.domain.User;
import io.miragon.timesync.adapter.out.clockify.models.UserDetails;
import io.miragon.timesync.domain.Workspace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class ClockifyAdapter implements LoadUsersPort, LoadWorkspacesPort, AggregateTimeEntriesPort {

    private final WebClient clockifyWebClient;

    @Override
    public List<Workspace> loadWorkspaces() {
        return clockifyWebClient.get()
                .uri("/workspaces")
                .retrieve()
                .bodyToFlux(Workspace.class)
                .collectList()
                .block();
    }

    @Override
    public List<User> loadUsers(LoadUsersCommand loadUserCommand) {
        var uri = String.format("/workspaces/%s/users", loadUserCommand.getWorkspace().getId());
        return clockifyWebClient.get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(User.class)
                .collectList()
                .block();
    }

    @Override
    public AggregateTimeEntriesResult aggregateTimeEntries(AggregateTimeEntriesCommand aggregateTimeEntriesCommand) {
        HashMap<String, String> aggregated = new HashMap<>();

        User user = aggregateTimeEntriesCommand.getUser();
        List<UserDetails> userDetails;
        userDetails = loadUserDetails(aggregateTimeEntriesCommand.getWorkspace(), user, aggregateTimeEntriesCommand.getFromDateTime(), aggregateTimeEntriesCommand.getToDateTime());

        for (UserDetails entry : userDetails) {
            if (Objects.isNull(entry.getTimeInterval().getEnd()) || Objects.isNull(entry.getTimeInterval().getDuration())) {
                continue;
            }

            aggregated.put(entry.getTimeInterval().getStart(), entry.getTimeInterval().getEnd());
        }
        return new AggregateTimeEntriesResult(aggregated);
    }

    private List<UserDetails> loadUserDetails(Workspace workspace, User user, LocalDateTime from, LocalDateTime to) throws WebClientRequestException
    {
        var formatter = DateTimeFormatter.ISO_INSTANT;
        var uri = String.format(
                "/workspaces/%s/user/%s/time-entries?page-size=5000&start=%s&end=%s",
                workspace.getId(),
                user.getId(),
                formatter.format(from.toInstant(ZoneOffset.UTC)),
                formatter.format(to.toInstant(ZoneOffset.UTC)));
        return clockifyWebClient.get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(UserDetails.class)
                .collectList()
                .block();
    }
}
