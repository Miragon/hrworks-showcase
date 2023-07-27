package io.miragon.timesync.adapter.out.hrworks.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WorkingTime {

    private String beginDateAndTime;

    private String endDateAndTime;

    private String personnelNumber;
}