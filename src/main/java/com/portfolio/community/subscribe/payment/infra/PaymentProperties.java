package com.portfolio.community.subscribe.payment.infra;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.charset.StandardCharsets;
import java.util.Base64;


@Getter
@Setter
@ConfigurationProperties(prefix = "payments.toss")
@ToString
public class PaymentProperties {

    private String secretKey;
    private String baseUrl;
    private String confirmEndpoint;

    public String getBase64SecretKey() {
        return Base64.getEncoder().encodeToString((secretKey+":").getBytes(StandardCharsets.UTF_8));
    }
}
