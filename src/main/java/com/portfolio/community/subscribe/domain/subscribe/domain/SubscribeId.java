package com.portfolio.community.subscribe.domain.subscribe.domain;


import com.portfolio.community.common.utils.RandomIdGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.Objects;

@Embeddable
@Getter
public class SubscribeId {

    @Column(name="subscribe_id")
    private String value;


    public SubscribeId() {
        this.value = RandomIdGenerator.generate(16);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubscribeId that = (SubscribeId) o;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }
}
