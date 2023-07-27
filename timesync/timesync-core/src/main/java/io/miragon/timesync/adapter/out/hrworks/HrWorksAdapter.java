package io.miragon.timesync.adapter.out.hrworks;

import io.miragon.timesync.application.port.out.HealthCheckPort;
import io.miragon.timesync.application.port.out.loademployeesdata.LoadEmployeesDataPort;
import io.miragon.timesync.application.port.out.sendworkingtimes.SendWorkingTimesCommand;
import io.miragon.timesync.application.port.out.sendworkingtimes.SendWorkingTimesPort;
import io.miragon.timesync.domain.EmployeeData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class HrWorksAdapter implements HealthCheckPort, LoadEmployeesDataPort, SendWorkingTimesPort {

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

    @Override
    public List<EmployeeData> loadEmployeesData() {
        return null;
    }

    @Override
    public void sendWorkingTimes(SendWorkingTimesCommand sendWorkingTimesCommand) {

    }
}