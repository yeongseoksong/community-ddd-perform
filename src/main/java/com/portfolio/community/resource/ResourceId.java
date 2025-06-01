package com.portfolio.community.resource;


import jakarta.persistence.Column;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class ResourceId {

    @Column(unique = true, nullable = false,name="resource_id")
    private String value;

    public ResourceId(Long value) {
        this.value = UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceId that = (ResourceId) o;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }
}
