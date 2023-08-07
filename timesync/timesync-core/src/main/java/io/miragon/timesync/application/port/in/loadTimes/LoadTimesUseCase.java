package io.miragon.timesync.application.port.in.loadTimes;

public interface LoadTimesUseCase
{
    LoadTimesResult loadTimes(LoadTimesCommand loadTimesCommand);
}
