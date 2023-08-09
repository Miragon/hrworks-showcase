package io.miragon.timesync.application.port.in.loadUsers;

import io.miragon.timesync.domain.EmployeeData;
import io.miragon.timesync.domain.User;
import io.miragon.timesync.domain.Workspace;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoadUsersResult
{
    private Workspace workspace;
    private List<User> users; // Users from Clockify
    private List<EmployeeData> employees; // Employees from HrWorks
}
