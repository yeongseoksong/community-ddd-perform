package com.portfolio.community.subscribe.subscribe.domain;

import com.portfolio.community.common.entity.BaseEntity;
import com.portfolio.community.common.entity.Period;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
public class Subscribe extends BaseEntity {

    @EmbeddedId
    private SubscribeId id;

    @Embedded
    private SubscriberInfo subscriberInfo;

    @Embedded
    private Period period;

    @Enumerated(EnumType.STRING)
    private SubscribeState state;

    public Subscribe( SubscriberInfo subscriberInfo, Period period) {
        if (subscriberInfo == null || period == null) {
            throw new IllegalArgumentException("subscriberInfo and startDateTime and endDateTime  are required");
        }

        this.subscriberInfo = subscriberInfo;
        this.period = period;
        this.state= SubscribeState.PENDING;
    }

}
