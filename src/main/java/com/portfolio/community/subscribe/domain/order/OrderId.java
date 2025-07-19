package com.portfolio.community.subscribe.domain.order;


import com.portfolio.community.common.utils.RandomIdGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.Objects;

@Embeddable
@Getter
public class OrderId {


    @Column(name="orderId")
    private String value;

    protected OrderId() {
        this.value = RandomIdGenerator.generate(16);
    }

    public OrderId(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderId orderId = (OrderId) o;
        return Objects.equals(getValue(), orderId.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }
}
