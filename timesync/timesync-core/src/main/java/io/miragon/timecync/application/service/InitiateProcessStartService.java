package io.miragon.timecync.application.service;

import io.miragon.timecync.application.port.in.startprocess.InitiateProcessStartCommand;
import io.miragon.timecync.application.port.in.startprocess.InitiateProcessStartUseCase;
import io.miragon.timecync.application.port.out.startprocess.InitiateProcessStartOutCommand;
import io.miragon.timecync.application.port.out.startprocess.InitiateProcessStartPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InitiateProcessStartService implements InitiateProcessStartUseCase {

    private final InitiateProcessStartPort initiateProcessStartPort;

    @Override
    public void initiateProcessStart(InitiateProcessStartCommand command) {
        var outCommand = new InitiateProcessStartOutCommand(command.getProcessKey(), command.getData());
        initiateProcessStartPort.initiateProcessStart(outCommand);
    }
}