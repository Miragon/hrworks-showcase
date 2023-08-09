package io.miragon.timesync.application.port.in.synctimes;

import io.miragon.timesync.domain.EmployeeData;
import io.miragon.timesync.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SyncTimesCommand
{
    private List<EmployeeData> employees;
    private User user;
    private Map<String, String> timeEntries;
}
