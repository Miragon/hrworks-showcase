package io.miragon.timecync.application.port.out;

import io.miragon.timecync.domain.Workspace;

import java.util.List;

public interface LoadWorkspacesPort {

    List<Workspace> loadWorkspaces();
}