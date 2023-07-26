package io.miragon.timesync.adapter.out.hrworks;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "hrworks")
@Setter
@Getter
public class HrWorksProperties {

    private String accessKey;

    private String secretAccessKey;

    private String baseUrl = "https://api-demo.hrworks.de/v2";
}