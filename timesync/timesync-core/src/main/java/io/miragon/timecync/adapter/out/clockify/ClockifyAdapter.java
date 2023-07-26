package io.miragon.timecync.adapter.out.clockify;

import io.miragon.timecync.application.port.out.LoadWorkspacesPort;
import io.miragon.timecync.application.port.out.aggregateTimeEntries.AggregateTimeEntriesCommand;
import io.miragon.timecync.application.port.out.aggregateTimeEntries.AggregateTimeEntriesPort;
import io.miragon.timecync.application.port.out.loadusers.LoadUsersCommand;
import io.miragon.timecync.application.port.out.loadusers.LoadUsersPort;
import io.miragon.timecync.domain.User;
import io.miragon.timecync.domain.Workspace;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class ClockifyAdapter implements LoadUsersPort, LoadWorkspacesPort, AggregateTimeEntriesPort {

    @Override
    public List<Workspace> loadWorkspaces() {
        return null;
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