package io.miragon.timecync.application.port.out.loadusers;

import io.miragon.timecync.domain.User;

import java.util.List;

public interface LoadUsersPort {

    List<User> loadUsers(LoadUsersCommand loadUserCommand);
}