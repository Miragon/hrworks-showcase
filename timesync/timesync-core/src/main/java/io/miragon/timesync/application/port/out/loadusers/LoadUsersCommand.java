package io.miragon.timesync.application.port.out.loadusers;

import io.miragon.timesync.domain.Workspace;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoadUsersCommand {

    private Workspace workspace;
}