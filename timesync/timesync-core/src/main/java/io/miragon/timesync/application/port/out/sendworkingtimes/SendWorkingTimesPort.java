package io.miragon.timesync.application.port.out.sendworkingtimes;

public interface SendWorkingTimesPort {

    void sendWorkingTimes(SendWorkingTimesCommand sendWorkingTimesCommand);
}