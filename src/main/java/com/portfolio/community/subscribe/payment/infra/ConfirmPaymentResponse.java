package com.portfolio.community.subscribe.payment.infra;

import com.portfolio.community.subscribe.payment.domain.Payment;
import com.portfolio.community.subscribe.payment.domain.PaymentException;
import com.portfolio.community.subscribe.payment.domain.PaymentState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@Getter
public class ConfirmPaymentResponse {
    private String orderId;
    private String paymentKey;
    private Long totalAmount;
    private PaymentState status;
    private OffsetDateTime requestedAt;
    private OffsetDateTime approvedAt;

    public Payment toEntity() {
        return new Payment(orderId, paymentKey, totalAmount, status,
                requestedAt.toLocalDateTime(), approvedAt.toLocalDateTime());
    }
}

