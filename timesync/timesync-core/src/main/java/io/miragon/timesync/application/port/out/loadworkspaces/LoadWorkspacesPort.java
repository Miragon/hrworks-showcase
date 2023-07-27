package io.miragon.timesync.application.port.out.loadworkspaces;

import io.miragon.timesync.domain.Workspace;

import java.util.List;

public interface LoadWorkspacesPort {

    List<Workspace> loadWorkspaces();
}