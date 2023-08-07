package io.miragon.timesync.application.port.in.loadTimes;

import io.miragon.timesync.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoadTimesCommand
{
    private User user;
}
