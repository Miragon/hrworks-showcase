package io.miragon.timecync.application.service;

import io.miragon.timecync.application.port.in.sendmessage.SendMessageCommand;
import io.miragon.timecync.application.port.in.sendmessage.SendMessageResult;
import io.miragon.timecync.application.port.in.sendmessage.SendMessageUseCase;
import io.miragon.timecync.application.port.out.sendmessage.SendMessageOutCommand;
import io.miragon.timecync.application.port.out.sendmessage.SendMessagePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SendMessageService implements SendMessageUseCase {

    private final SendMessagePort sendMessagePort;

    @Override
    public SendMessageResult sendMessage(SendMessageCommand command) {
        var outCommand = new SendMessageOutCommand(
                command.getMessageName(),
                command.getKey(),
                command.getData());
        sendMessagePort.sendMessage(outCommand);
        return new SendMessageResult("answer to: " + command.getMessageName());
    }
}