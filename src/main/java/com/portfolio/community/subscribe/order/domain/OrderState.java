package com.portfolio.community.subscribe.order.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderState {

    PENDING(false,"처리중"),
    FAIL(false,"결제실패"),
    APPROVED(true,"결제성공");

    private final Boolean isAccessible;
    private final String message;
}
