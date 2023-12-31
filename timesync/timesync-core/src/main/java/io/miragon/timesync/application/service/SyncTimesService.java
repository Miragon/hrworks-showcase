package io.miragon.timesync.application.service;

import io.miragon.timesync.application.port.in.synctimes.*;
import io.miragon.timesync.application.port.out.sendworkingtimes.SendWorkingTimesCommand;
import io.miragon.timesync.application.port.out.sendworkingtimes.SendWorkingTimesPort;
import io.miragon.timesync.domain.EmployeeData;
import io.miragon.timesync.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Slf4j
public
class SyncTimesService implements SyncTimesUseCase {

    private final SendWorkingTimesPort sendWorkingtimesPort;

    @Override
    public void syncTimes(SyncTimesCommand syncTimesCommand) {
        User user = syncTimesCommand.getUser();
        List<EmployeeData> employees = syncTimesCommand.getEmployees();

        log.info("[SYNC-TIMES] Sending working times...");
        for (var data : employees) {
            if (Objects.equals(user.getEmail(), data.getEmail())) {
                var aggregatedUserTimes = syncTimesCommand.getTimeEntries();
                aggregatedUserTimes.forEach((beginDateAndTime, endDateAndTime) -> sendWorkingtimesPort.sendWorkingTimes(
                        new SendWorkingTimesCommand(
                                beginDateAndTime,
                                endDateAndTime,
                                data.getPersonnelNumber())));
            }
        }
        log.info("[SYNC-TIMES] Syncing times finished");
    }
}
