package com.portfolio.community.subscribe.domain;

import com.portfolio.community.common.entity.BaseEntity;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
public class Subscribe extends BaseEntity {

    @EmbeddedId
    private SubscribeId id;

    @Embedded
    private SubscriberInfo subscriberInfo;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    public Subscribe( SubscriberInfo subscriberInfo, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (subscriberInfo == null || startDateTime == null || endDateTime == null) {
            throw new IllegalArgumentException("subscriberInfo and startDateTime and endDateTime  are required");
        }
        if(startDateTime.isBefore(endDateTime)) {
            throw new IllegalArgumentException("startDateTime must be before endDateTime");
        }

        this.subscriberInfo = subscriberInfo;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }
}
