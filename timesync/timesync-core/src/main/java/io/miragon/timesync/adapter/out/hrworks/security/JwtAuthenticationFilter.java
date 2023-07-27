package io.miragon.timesync.adapter.out.hrworks.security;

import io.miragon.timesync.adapter.out.hrworks.HrWorksProperties;
import io.miragon.timesync.adapter.out.hrworks.models.TokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.*;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
public class JwtAuthenticationFilter implements ExchangeFilterFunction {

    private static final long JWT_TOKEN_TTL = TimeUnit.MINUTES.toMillis(15);

    private final HrWorksProperties hrWorksProperties;
    private final WebClient webClient;
    private String jwtToken;
    private long jwtTokenExpire;

    public JwtAuthenticationFilter(final HrWorksProperties hrWorksProperties) {
        this.hrWorksProperties = hrWorksProperties;
        this.webClient = WebClient.builder()
                .baseUrl(hrWorksProperties.getBaseUrl())
                .build();
    }

    private String fetchToken() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("accessKey", hrWorksProperties.getAccessKey());
        requestBody.put("secretAccessKey", hrWorksProperties.getSecretAccessKey());

        var token = webClient.post()
                .uri("/authentication")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(TokenResponse.class)
                .block();

        jwtTokenExpire = System.currentTimeMillis() + JWT_TOKEN_TTL;

        log.info("[AUTH] JWT renewed.");

        return token.getToken();
    }

    private boolean isTokenExpired(String token) {
        var expired = System.currentTimeMillis() > jwtTokenExpire;
        return Objects.isNull(token) || expired;
    }

    @Override
    public Mono<ClientResponse> filter(ClientRequest request, ExchangeFunction next) {
        if (isTokenExpired(jwtToken)) {
            jwtToken = fetchToken();
        }

        ClientRequest authenticatedRequest = ClientRequest.from(request)
                .headers(headers -> headers.setBearerAuth(jwtToken))
                .build();

        return next.exchange(authenticatedRequest);
    }
}