package io.miragon.timesync.application.port.in.loadUsers;

import io.miragon.timesync.domain.User;
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
    private List<User> users;
}
