package com.portfolio.community.subscribe.domain.subscribe.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubscriberInfo {

    @Embedded
    private SubscriberId subscriberId;

    @Column(name="subscriber_name")
    private String name;


    public SubscriberInfo(SubscriberId subscriberId, String name) {
       if(subscriberId == null|| name == null) {
           throw new IllegalArgumentException("Subscriber id and name cannot be null");
       }
        this.subscriberId = subscriberId;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubscriberInfo that = (SubscriberInfo) o;
        return Objects.equals(getSubscriberId(), that.getSubscriberId()) && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getSubscriberId());
        result = 31 * result + Objects.hashCode(getName());
        return result;
    }
}
