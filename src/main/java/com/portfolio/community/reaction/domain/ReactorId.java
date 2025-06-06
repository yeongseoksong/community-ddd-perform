package com.portfolio.community.reaction.domain;

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
public class ReactorId {
    @AttributeOverride(name="value",column = @Column(name="reactor_id"))
    private MemberId memberId;


    public ReactorId(MemberId memberId) {
        this.memberId = memberId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReactorId reactorId = (ReactorId) o;
        return Objects.equals(getMemberId(), reactorId.getMemberId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getMemberId());
    }
}
