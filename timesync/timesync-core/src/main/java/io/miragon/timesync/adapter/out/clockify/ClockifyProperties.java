package io.miragon.timesync.adapter.out.clockify;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "clockify")
@Setter
@Getter
public class ClockifyProperties {

    private String baseUrl = "https://api.clockify.me/api/v1/";

    private String ApiKey;
}