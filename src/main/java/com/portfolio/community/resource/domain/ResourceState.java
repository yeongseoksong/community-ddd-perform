package com.portfolio.community.resource.domain;

import lombok.Getter;

@Getter
public enum ResourceState {
    SAVING(false),
    ACTIVE(true),
    DELETE(false),
    ERROR(false);


    private final boolean isShow;

    ResourceState(boolean isShow) {
        this.isShow = isShow;
    }

     public boolean isShow() {
        return isShow;
    }
}
