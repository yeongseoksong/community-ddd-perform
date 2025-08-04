package com.portfolio.community.subscribe.payment.infra;

import com.portfolio.community.subscribe.order.domain.OrderId;
import com.portfolio.community.subscribe.payment.domain.PaymentClient;
import com.portfolio.community.subscribe.payment.domain.PaymentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;


@Component
@RequiredArgsConstructor
@Slf4j
public class TossPaymentClient implements PaymentClient {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BASIC_AUTH_PREFIX ="Basic ";
    private static final String IDEMPOTENCY_KEY ="Idempotency-Key";
    private final PaymentProperties paymentProperties;


    public ConfirmPaymentResponse confirm(OrderId orderId, String paymentKey, Long amount){

        ConfirmPaymentRequest requestBody = new ConfirmPaymentRequest(
                orderId,paymentKey , amount
        );
        try {
            ConfirmPaymentResponse response = tossClient().post()
                    .uri(paymentProperties.getBaseUrl() + paymentProperties.getConfirmEndpoint())
                    .header(AUTHORIZATION_HEADER, BASIC_AUTH_PREFIX+paymentProperties.getBase64SecretKey())
                    .header(IDEMPOTENCY_KEY, orderId.getValue())
                    .body(requestBody)
                    .retrieve()
                    .body(ConfirmPaymentResponse.class);

            log.info("결제 승인 완료: {}", response);
            return response;
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("{}", e.getMessage());
            throw new PaymentException();
        } catch (Exception e) {
            throw new PaymentException();
        }
    }



    private RestClient tossClient() {
        RestClient restClient = RestClient.builder()
                .baseUrl(paymentProperties.getBaseUrl())
                .defaultHeader(AUTHORIZATION_HEADER,
                        BASIC_AUTH_PREFIX + paymentProperties.getBase64SecretKey())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        return restClient;
    }
}
