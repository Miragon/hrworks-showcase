package io.miragon.timecync.application.port.in.sendmessage;

public interface SendMessageUseCase {

    SendMessageResult sendMessage(final SendMessageCommand command);
}
