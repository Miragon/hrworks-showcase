package io.miragon.timesync.adapter.out.hrworks;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "hrworks")
@Setter
@Getter
@Valid
public class HrWorksProperties {

    @NotNull
    private String accessKey;

    @NotNull
    private String secretAccessKey;

    private String baseUrl = "https://api-demo.hrworks.de/v2";
}