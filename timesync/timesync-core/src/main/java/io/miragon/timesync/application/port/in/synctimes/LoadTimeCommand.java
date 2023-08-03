package io.miragon.timesync.application.port.in.synctimes;

import io.miragon.timesync.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoadTimeCommand
{
    private User user;
}
