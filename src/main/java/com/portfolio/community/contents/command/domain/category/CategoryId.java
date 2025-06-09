package com.portfolio.community.contents.command.domain.category;

import com.portfolio.community.common.utils.RandomIdGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.Objects;

@Embeddable
@Getter
public class CategoryId {

    @Column(name="category_id")
    private String value;

    public CategoryId(String value) {
        if(value == null)
            throw new IllegalArgumentException("CategoryId cannot be null");

        this.value = value;
    }

    public CategoryId() {
        this(RandomIdGenerator.generate());
    }

    public static CategoryId of(String value) {
        return new CategoryId(value);
    }



    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        CategoryId that = (CategoryId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

}
