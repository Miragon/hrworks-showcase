package io.miragon.timecync.application.port.in.dosomething;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DoSomethingCommand {

    private String command;
}