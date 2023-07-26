package io.miragon.timecync.application.port.in.dosomething;

public interface DoSomethingUseCase {

    DoSomethingResult doSomething(DoSomethingCommand command);
}
