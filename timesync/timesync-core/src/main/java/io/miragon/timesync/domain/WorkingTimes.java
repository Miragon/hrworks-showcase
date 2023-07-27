package io.miragon.timesync.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WorkingTimes {

    private String beginDateAndTime;

    private String endDateAndTime;

    private String personnelNumber;
}