package io.miragon.timesync.adapter.in.miranum;

import io.miragon.miranum.connect.worker.api.BusinessException;
import io.miragon.miranum.connect.worker.api.Worker;
import io.miragon.timesync.application.port.in.loadTimes.LoadTimesCommand;
import io.miragon.timesync.application.port.in.loadTimes.LoadTimesResult;
import io.miragon.timesync.application.port.in.loadTimes.LoadTimesUseCase;
import io.miragon.timesync.application.port.in.loadUsers.LoadUsersUseCase;
import io.miragon.timesync.application.port.in.synctimes.*;
import io.miragon.timesync.application.port.in.loadUsers.LoadUsersResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RequiredArgsConstructor
public class MiranumWorkerAdapter {

    private final LoadUsersUseCase loadUsersUseCase;
    private final LoadTimesUseCase loadTimesUseCase;
    private final SyncTimesUseCase syncTimesUseCase;

    @Worker(type = "loadUsers")
    public LoadUsersResult loadUsers(Object o) { return loadUsersUseCase.loadUsers(); }

    @Worker(type = "loadTimes")
    public LoadTimesResult loadTimes(LoadTimesCommand loadTimesCommand)
    {
        try
        {
            return loadTimesUseCase.loadTimes(loadTimesCommand);
        } catch (WebClientResponseException exception) {
            String message = "User data could not be retrieved due to " + exception.getMessage();
            throw new BusinessException(exception.getStatusCode().toString(), message);
        }
    }

    @Worker(type = "syncTimes")
    public void syncTimes(SyncTimesCommand syncTimesCommand) {
        syncTimesUseCase.syncTimes(syncTimesCommand);
    }
}
