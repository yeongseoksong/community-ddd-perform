package com.portfolio.community.subscribe.domain.subscribe.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SubscribeState {
    PENDING(false),
    ACTIVE(true),
    CANCELLED(false),
    EXPIRED(false);

    private final Boolean isAccessible;

}
