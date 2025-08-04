package com.portfolio.community.subscribe.payment.domain;


import lombok.Getter;

@Getter
public enum PaymentState {
    READY(false),
    IN_PROGRESS(false),
    WAITING_FOR_DEPOSIT(false),
    DONE(true),
    CANCELED(false),
    PARTIAL_CANCELED(false),
    ABORTED(false),
    EXPIRED(false);

    private final boolean isSuccess;

    PaymentState(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
