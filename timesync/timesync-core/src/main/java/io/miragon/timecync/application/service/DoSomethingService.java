package io.miragon.timecync.application.service;

import io.miragon.timecync.application.port.in.dosomething.DoSomethingCommand;
import io.miragon.timecync.application.port.in.dosomething.DoSomethingResult;
import io.miragon.timecync.application.port.in.dosomething.DoSomethingUseCase;

public class DoSomethingService implements DoSomethingUseCase {

    public DoSomethingResult doSomething(DoSomethingCommand command) {
        String result = command.getCommand() + " result";
        return new DoSomethingResult(result);
    }
}