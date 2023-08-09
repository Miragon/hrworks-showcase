package io.miragon.timesync.application.port.in.loadTimes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoadTimesResult
{
    Map<String, String> timeEntries;
}
