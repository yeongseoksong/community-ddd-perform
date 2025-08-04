package com.portfolio.community.subscribe.payment.domain;

import com.portfolio.community.common.response.MotherException;

public class PaymentException extends MotherException {
    private static final String DEFAULT_MESSAGE = "Payment failed";

    public PaymentException() {
        super(DEFAULT_MESSAGE);
    }
}
