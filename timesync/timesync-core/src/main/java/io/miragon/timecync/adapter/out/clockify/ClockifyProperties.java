package io.miragon.timecync.adapter.out.clockify;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "clockify")
public class ClockifyProperties {

    private String ApiKey;

    private String baseUrl = "https://api.clockify.me/api/v1/";
}