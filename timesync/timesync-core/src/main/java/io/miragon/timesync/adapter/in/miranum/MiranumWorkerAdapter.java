package io.miragon.timesync.adapter.in.miranum;

import io.miragon.miranum.connect.elementtemplate.api.ElementTemplate;
import io.miragon.miranum.connect.worker.api.BusinessException;
import io.miragon.miranum.connect.worker.api.Worker;
import io.miragon.timesync.application.port.in.synctimes.LoadTimeCommand;
import io.miragon.timesync.application.port.in.synctimes.SyncTimesCommand;
import io.miragon.timesync.application.port.in.synctimes.SyncTimesUseCase;
import io.miragon.timesync.application.port.out.aggregateTimeEntries.AggregateTimeEntriesResult;
import io.miragon.timesync.domain.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RequiredArgsConstructor
public class MiranumWorkerAdapter {

    private final SyncTimesUseCase syncTimesUseCase;

    @Worker(type = "loadUsers")
    public UserResponse loadUsers(Object o) { return syncTimesUseCase.loadUsers(); }

    @Worker(type = "loadTimes")
    @ElementTemplate(name = "Load Times", description = "Load the working ours of a specific employee.")
    public AggregateTimeEntriesResult loadTimes(LoadTimeCommand loadTimeCommand)
    {
        try
        {
            return syncTimesUseCase.loadTime(loadTimeCommand);
        } catch (WebClientResponseException exception) {
            String message = "User data could not be retrieved due to " + exception.getMessage();
            throw new BusinessException(exception.getStatusCode().toString(), message);
        }
    }

    @Worker(type = "syncTimes")
    @ElementTemplate(name = "Sync Times", description = "Sync the working ours of a specific employee.")
    public void syncTimes(SyncTimesCommand syncTimesCommand) {
        syncTimesUseCase.syncTimes(syncTimesCommand);
    }

}
