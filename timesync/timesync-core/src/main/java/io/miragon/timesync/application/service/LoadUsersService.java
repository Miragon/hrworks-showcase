package io.miragon.timesync.application.service;

import io.miragon.timesync.application.port.out.loademployeesdata.LoadEmployeesDataPort;
import io.miragon.timesync.application.port.out.loadusers.LoadUsersCommand;
import io.miragon.timesync.application.port.out.loadusers.LoadUsersPort;
import io.miragon.timesync.application.port.out.loadworkspaces.LoadWorkspacesPort;
import io.miragon.timesync.domain.EmployeesData;
import io.miragon.timesync.domain.User;
import io.miragon.timesync.domain.Workspace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import io.miragon.timesync.application.port.in.loadUsers.LoadUsersResult;
import io.miragon.timesync.application.port.in.loadUsers.LoadUsersUseCase;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class LoadUsersService implements LoadUsersUseCase
{
    private final LoadWorkspacesPort loadWorkspacesPort;
    private final LoadUsersPort loadUsersPort;
    private final LoadEmployeesDataPort loadEmployeesDataPort;

    @Override
    public LoadUsersResult loadUsers()
    {
        log.info("[LOAD-USERS] Syncing times...");
        log.info("[LOAD-USERS] Loading workspaces...");
        List<Workspace> workspaces = loadWorkspacesPort.loadWorkspaces();
        Workspace workspace = workspaces.get(0);

        log.info("[LOAD-USERS] Loading users from Clockify...");
        List<User> users = loadUsersPort.loadUsers(new LoadUsersCommand(workspace));
        log.info("[LOAD-USERS] Loading employees from HrWorks...");
        EmployeesData employeesData = loadEmployeesDataPort.loadEmployeesData();

        // Force an error when loading times (only for the showcase)
        users.add(new User("123", "error@miragon.io"));

        return new LoadUsersResult(workspace, users, employeesData.getPersons());
    }
}
