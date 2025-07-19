package com.portfolio.community.subscribe.domain.payment.infra;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@Setter
@ConfigurationProperties(prefix = "payments.toss")
@ToString
public class PaymentProperties {

    private String secretKey;
    private String baseUrl;
    private String confirmEndpoint;

}
