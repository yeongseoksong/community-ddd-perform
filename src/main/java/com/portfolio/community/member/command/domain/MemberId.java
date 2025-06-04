package com.portfolio.community.member.command.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Getter
@Embeddable
public class MemberId {

    @Column(name="member_id")
    private String value;

    public MemberId() {
        this.value=UUID.randomUUID().toString();
    }

    private MemberId(String memberId) {
        this.value = memberId;
    }

    public static MemberId of(String memberId) {
        return new MemberId(memberId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemberId memberId = (MemberId) o;
        return Objects.equals(getValue(), memberId.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }
}
