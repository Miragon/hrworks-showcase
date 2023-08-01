package io.miragon.timesync.application.port.out.sendworkingtimes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SendWorkingTimesCommand {

    private String beginDateAndTime;

    private String endDateAndTime;

    private String personnelNumber;
}