package io.miragon.timecync;

import io.miragon.timecync.adapter.in.miranum.MiranumInAutoConfiguration;
import io.miragon.timecync.adapter.out.miranum.MiranumOutAutoConfiguration;
import io.miragon.timecync.application.port.in.dosomething.DoSomethingUseCase;
import io.miragon.timecync.application.port.in.sendmessage.SendMessageUseCase;
import io.miragon.timecync.application.port.in.startprocess.InitiateProcessStartUseCase;
import io.miragon.timecync.application.port.out.sendmessage.SendMessagePort;
import io.miragon.timecync.application.port.out.startprocess.InitiateProcessStartPort;
import io.miragon.timecync.application.service.DoSomethingService;
import io.miragon.timecync.application.service.InitiateProcessStartService;
import io.miragon.timecync.application.service.SendMessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        MiranumInAutoConfiguration.class,
        MiranumOutAutoConfiguration.class
})
public class ExampleAutoConfiguration {

    @Bean
    public DoSomethingUseCase doSomethingUseCase() {
        return new DoSomethingService();
    }

    @Bean
    public SendMessageUseCase sendMessageUseCase(final SendMessagePort sendMessagePort) {
        return new SendMessageService(sendMessagePort);
    }

    @Bean
    public InitiateProcessStartUseCase initiateProcessStartUseCase(final InitiateProcessStartPort initiateProcessStartPort) {
        return new InitiateProcessStartService(initiateProcessStartPort);
    }
}