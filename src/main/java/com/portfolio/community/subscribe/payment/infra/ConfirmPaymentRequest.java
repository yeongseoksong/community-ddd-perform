package com.portfolio.community.subscribe.payment.infra;

import com.portfolio.community.subscribe.order.domain.OrderId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class ConfirmPaymentRequest {
    private String orderId;
    private String paymentKey;
    private Long amount;

    ConfirmPaymentRequest(String orderId, String paymentKey, Long amount) {
        if(orderId==null || paymentKey==null || amount==null) {
            throw new IllegalArgumentException("orderId and paymentKey must not be null");
        }
        this.orderId = orderId;
        this.paymentKey = paymentKey;
        this.amount = amount;
    }

    public ConfirmPaymentRequest(OrderId orderId, String paymentKey, Long amount) {
        this(orderId.getValue(), paymentKey, amount);
    }
}
