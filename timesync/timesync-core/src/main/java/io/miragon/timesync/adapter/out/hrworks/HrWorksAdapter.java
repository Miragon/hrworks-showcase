package io.miragon.timesync.adapter.out.hrworks;

import io.miragon.timesync.adapter.out.hrworks.models.CreateWorkingTimesRequest;
import io.miragon.timesync.adapter.out.hrworks.models.WorkingTime;
import io.miragon.timesync.application.port.out.healthcheck.HealthCheckPort;
import io.miragon.timesync.application.port.out.loademployeesdata.LoadEmployeesDataPort;
import io.miragon.timesync.application.port.out.sendworkingtimes.SendWorkingTimesCommand;
import io.miragon.timesync.application.port.out.sendworkingtimes.SendWorkingTimesPort;
import io.miragon.timesync.domain.EmployeesData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class HrWorksAdapter implements HealthCheckPort, LoadEmployeesDataPort, SendWorkingTimesPort {

    private final WebClient hrWorksWebClient;

    private static String toHRWorksIsoTimeString(String isoTime) {
        return isoTime
                .replace(":", "")
                .replace("-", "");
    }

    @Override
    public void healthCheck() {
        var response = hrWorksWebClient.get()
                .uri("/health-check")
                .retrieve()
                .toBodilessEntity()
                .block();

        log.info("[HRWORKS] Healthcheck status code: {}", response.getStatusCode());
    }

    @Override
    public EmployeesData loadEmployeesData() {
        return hrWorksWebClient.get()
                .uri("/persons/master-data")
                .retrieve()
                .bodyToMono(EmployeesData.class)
                .block();
    }

    @Override
    public void sendWorkingTimes(SendWorkingTimesCommand sendWorkingTimesCommand) {
        var workingTime = new WorkingTime(
                toHRWorksIsoTimeString(sendWorkingTimesCommand.getBeginDateAndTime()),
                toHRWorksIsoTimeString(sendWorkingTimesCommand.getEndDateAndTime()),
                sendWorkingTimesCommand.getPersonnelNumber());
        hrWorksWebClient.post()
                .uri("/working-times")
                .bodyValue(new CreateWorkingTimesRequest(List.of(workingTime)))
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}