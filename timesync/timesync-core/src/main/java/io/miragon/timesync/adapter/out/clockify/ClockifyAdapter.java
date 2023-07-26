package io.miragon.timesync.adapter.out.clockify;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.miragon.timesync.application.port.out.LoadWorkspacesPort;
import io.miragon.timesync.application.port.out.aggregateTimeEntries.AggregateTimeEntriesCommand;
import io.miragon.timesync.application.port.out.aggregateTimeEntries.AggregateTimeEntriesPort;
import io.miragon.timesync.application.port.out.loadusers.LoadUsersCommand;
import io.miragon.timesync.application.port.out.loadusers.LoadUsersPort;
import io.miragon.timesync.domain.User;
import io.miragon.timesync.domain.UserDetails;
import io.miragon.timesync.domain.Workspace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    public List<User> loadUsers(LoadUsersCommand loadUserCommand) {
        var uri = String.format("/workspaces/%s/users", loadUserCommand.getWorkspace().getId());
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(User.class)
                .collectList()
                .block();
    }

    @Override
    public Map<String, Map<String, Duration>> aggregateTimeEntries(AggregateTimeEntriesCommand aggregateTimeEntriesCommand) {
        Map<String, Map<String, Duration>> aggregated = new HashMap<>();
        for (User user : aggregateTimeEntriesCommand.getUsers()) {
            List<UserDetails> userDetails = null;
            try {
                userDetails = loadUserDetails(
                        aggregateTimeEntriesCommand.getWorkspace(),
                        user,
                        aggregateTimeEntriesCommand.getFromDateTime(),
                        aggregateTimeEntriesCommand.getToDateTime());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            HashMap<String, Duration> map = new HashMap<>();
            for (UserDetails entry : userDetails) {
                if (Objects.isNull(entry.getTimeInterval().getEnd()) || Objects.isNull(entry.getTimeInterval().getDuration())) {
                    continue;
                }

                var date = LocalDateTime.parse(entry.getTimeInterval().getStart(), DateTimeFormatter.ISO_DATE_TIME).toString();
                Duration duration = Duration.parse(entry.getTimeInterval().getDuration());

                if (map.containsKey(date)) {
                    var existingDuration = map.get(date).plus(duration);
                    map.put(date, map.get(date).plus(existingDuration));
                } else {
                    map.put(date, duration);
                }

                aggregated.put(user.getEmail(), map);
            }
        }
        return aggregated;
    }

    private List<UserDetails> loadUserDetails(Workspace workspace, User user, LocalDateTime from, LocalDateTime to) throws JsonProcessingException {
        var fromInstant = from.toInstant(ZoneOffset.UTC);
        var toInstant = to.toInstant(ZoneOffset.UTC);
        var formatter = DateTimeFormatter.ISO_INSTANT;
        var uri = String.format("/workspaces/%s/user/%s/time-entries?page-size=5000&start=%s&end=%s",
                workspace.getId(),
                user.getId(),
                formatter.format(fromInstant),
                formatter.format(toInstant));
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(UserDetails.class)
                .collectList()
                .block();
    }
}