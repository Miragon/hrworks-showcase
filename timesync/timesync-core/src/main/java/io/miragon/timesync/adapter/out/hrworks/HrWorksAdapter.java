package io.miragon.timesync.adapter.out.hrworks;

import io.miragon.timesync.application.port.out.HealthCheckPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Slf4j
public class HrWorksAdapter implements HealthCheckPort {

    private final WebClient hrWorksWebClient;

    @Override
    public void healthCheck() {
        var response = hrWorksWebClient.get()
                .uri("/health-check")
                .retrieve()
                .toBodilessEntity()
                .block();

        log.info("Healthcheck status code: {}", response.getStatusCode());
    }
}