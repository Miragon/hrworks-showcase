package io.miragon.timecync.application.port.out.sendmessage;

public interface SendMessagePort {

    void sendMessage(final SendMessageOutCommand command);
}