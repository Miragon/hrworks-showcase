package io.miragon.timesync.adapter.out.clockify;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "clockify")
@Setter
@Getter
@Valid
public class ClockifyProperties {

    private String baseUrl = "https://api.clockify.me/api/v1/";

    @NotNull
    private String ApiKey;
}