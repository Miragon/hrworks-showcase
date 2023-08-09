package io.miragon.timesync.application.port.in.loadTimes;

import io.miragon.timesync.domain.User;
import io.miragon.timesync.domain.Workspace;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoadTimesCommand
{
    private Workspace workspace;
    private User user;
}
