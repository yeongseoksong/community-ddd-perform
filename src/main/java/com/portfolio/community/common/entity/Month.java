package com.portfolio.community.common.entity;


import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@Getter
public class Month {
    Long value;

    public Month(Long value) {
        if(value==null)
            throw new IllegalStateException("value cannot be null");
        if(value<=0)
            throw new IllegalArgumentException("value must be greater than zero");

        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Month month = (Month) o;
        return Objects.equals(value, month.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
