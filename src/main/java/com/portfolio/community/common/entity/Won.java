package com.portfolio.community.common.entity;


import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@Getter
public class Won {
    private Long value;

    public Won(Long value) {
        if(value == null)
            throw new IllegalArgumentException("Value cannot be null");

        this.value = value;
    }

    public Won add(Won won) {
        if(won == null)
            throw new IllegalArgumentException("Won cannot be null");

        Long sum = this.value + won.value;
        return new Won(sum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Won won = (Won) o;
        return Objects.equals(value, won.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }


}
