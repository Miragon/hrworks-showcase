package io.miragon.timesync.application.port.in.synctimes;

import io.miragon.timesync.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SyncTimesCommand
{
    private User user;

    private Map<String, String> timeEntries;
}
