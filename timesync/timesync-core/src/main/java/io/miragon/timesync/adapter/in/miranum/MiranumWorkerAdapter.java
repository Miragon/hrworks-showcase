package io.miragon.timesync.adapter.in.miranum;

import io.miragon.miranum.connect.worker.api.Worker;
import io.miragon.timesync.application.port.in.synctimes.SyncTimesUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MiranumWorkerAdapter {

    private final SyncTimesUseCase syncTimesUseCase;

    @Worker(type = "syncTimes")
    public void syncTimes(Object o) {
        syncTimesUseCase.syncTimes();
    }
}