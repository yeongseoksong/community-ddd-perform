package com.portfolio.community.resource.domain;


import com.portfolio.community.common.utils.RandomIdGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.Objects;

@Getter
@Embeddable
public class ResourceId {

    @Column(unique = true, nullable = false,name="resource_id")
    private String value;

    public ResourceId() {
        this.value = RandomIdGenerator.generate();
    }
    public  ResourceId(String value){
        this.value=value;
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
