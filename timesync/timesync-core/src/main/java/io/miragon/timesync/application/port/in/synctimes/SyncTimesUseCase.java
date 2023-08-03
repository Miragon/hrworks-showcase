package io.miragon.timesync.application.port.in.synctimes;

import io.miragon.timesync.application.port.out.aggregateTimeEntries.AggregateTimeEntriesResult;
import io.miragon.timesync.domain.UserResponse;

public interface SyncTimesUseCase {

    UserResponse loadUsers();

    AggregateTimeEntriesResult loadTime(LoadTimeCommand loadTimeCommand);

    void syncTimes(SyncTimesCommand syncTimesCommand);
}
