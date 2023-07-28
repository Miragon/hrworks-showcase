package io.miragon.timesync.adapter.out.clockify.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TimeInterval {

    private String start;

    private String duration;

    private String end;
}