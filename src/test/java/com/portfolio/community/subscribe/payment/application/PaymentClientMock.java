package com.portfolio.community.subscribe.payment.application;

import com.portfolio.community.subscribe.order.domain.OrderId;
import com.portfolio.community.subscribe.payment.domain.PaymentClient;
import com.portfolio.community.subscribe.payment.domain.PaymentState;
import com.portfolio.community.subscribe.payment.infra.ConfirmPaymentResponse;

import java.time.OffsetDateTime;

public class PaymentClientMock implements PaymentClient {

    @Override
    public ConfirmPaymentResponse confirm(OrderId orderId, String paymentKey, Long amount) {
        return new ConfirmPaymentResponse(orderId.getValue(),paymentKey,amount, PaymentState.DONE, OffsetDateTime.now(),OffsetDateTime.now());
    }
}
