package io.miragon.timesync.adapter.in.miranum;

import io.miragon.miranum.connect.worker.api.Worker;
import io.miragon.timesync.application.port.in.synctimes.LoadTimeCommand;
import io.miragon.timesync.application.port.in.synctimes.SyncTimesCommand;
import io.miragon.timesync.application.port.in.synctimes.SyncTimesUseCase;
import io.miragon.timesync.application.port.out.aggregateTimeEntries.AggregateTimeEntriesResult;
import io.miragon.timesync.domain.UserResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MiranumWorkerAdapter {

    private final SyncTimesUseCase syncTimesUseCase;

    @Worker(type = "loadUsers")
    public UserResponse loadUsers(Object o) { return syncTimesUseCase.loadUsers(); }

    @Worker(type = "loadTime")
    public AggregateTimeEntriesResult loadTime(LoadTimeCommand loadTimeCommand) {
        return syncTimesUseCase.loadTime(loadTimeCommand);
    }

    @Worker(type = "syncTimes")
    public void syncTimes(SyncTimesCommand syncTimesCommand) {
        syncTimesUseCase.syncTimes(syncTimesCommand);
    }

}
