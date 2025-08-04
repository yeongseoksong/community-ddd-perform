package com.portfolio.community.subscribe.payment.domain;

import com.portfolio.community.subscribe.order.domain.OrderId;
import com.portfolio.community.subscribe.payment.infra.ConfirmPaymentResponse;

public interface PaymentClient {

    ConfirmPaymentResponse confirm(OrderId orderId, String paymentKey, Long amount);
}
