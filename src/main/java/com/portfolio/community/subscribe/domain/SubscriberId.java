package com.portfolio.community.subscribe.domain;


import com.portfolio.community.member.command.domain.MemberId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;


@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SubscriberId {

    @AttributeOverride(name="value", column = @Column(name="member_id"))
    private MemberId memberId;

    public SubscriberId(MemberId memberId) {
        this.memberId =memberId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubscriberId that = (SubscriberId) o;
        return Objects.equals(memberId, that.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(memberId);
    }
}
