package io.miragon.timecync.adapter.in.miranum;

import io.miragon.miranum.connect.worker.api.Worker;
import io.miragon.timecync.application.port.in.synctimes.SyncTimesUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MiranumWorkerAdapter {

    private final SyncTimesUseCase syncTimesUseCase;

    @Worker(type = "syncTimes")
    public void doSomething() {
        syncTimesUseCase.syncTimes();
    }
}