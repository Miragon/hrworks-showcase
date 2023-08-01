package io.miragon.timesync.application.port.out.loadusers;

import io.miragon.timesync.domain.User;

import java.util.List;

public interface LoadUsersPort {

    List<User> loadUsers(LoadUsersCommand loadUserCommand);
}