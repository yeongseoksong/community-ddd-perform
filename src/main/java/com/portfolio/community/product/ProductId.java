package com.portfolio.community.product;


import com.portfolio.community.common.utils.RandomIdGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.Objects;

@Getter
@Embeddable
public class ProductId {

    @Column(name="product_id")
    private String value;

    public ProductId() {
        this.value = RandomIdGenerator.generate(24);;
    }

    public ProductId(String value) {
        if(value == null) {
            throw new NullPointerException("value is null");
        }
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductId productId = (ProductId) o;
        return Objects.equals(value, productId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
