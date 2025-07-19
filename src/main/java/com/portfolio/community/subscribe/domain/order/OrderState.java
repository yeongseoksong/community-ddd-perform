package com.portfolio.community.subscribe.domain.order;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderState {

    PENDING(false),
    CANCELLED(false),
    APPROVED(true);

    private final Boolean isAccessible;

}
