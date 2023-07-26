package io.miragon.timecync.application.port.out.startprocess;

public interface InitiateProcessStartPort {

    void initiateProcessStart(final InitiateProcessStartOutCommand command);
}