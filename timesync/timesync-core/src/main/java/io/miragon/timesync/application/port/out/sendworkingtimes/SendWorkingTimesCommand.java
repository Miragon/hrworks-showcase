package io.miragon.timesync.application.port.out.sendworkingtimes;

import io.miragon.timesync.domain.WorkingTimes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@Getter
@Setter
public class SendWorkingTimesCommand {

    private List<WorkingTimes> workingTimesList;
}