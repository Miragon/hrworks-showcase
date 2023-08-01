package io.miragon.timesync.adapter.out.hrworks.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateWorkingTimesRequest {

    private final List<WorkingTime> data;

    private boolean deleteOverlappingWorkingTimes = true;
}