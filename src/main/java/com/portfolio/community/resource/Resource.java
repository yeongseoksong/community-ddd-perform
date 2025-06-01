package com.portfolio.community.resource;

import com.portfolio.community.common.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="resource_type")
@Table(name = "resource")
public abstract  class Resource extends BaseEntity {
    @EmbeddedId
    private ResourceId id;



}
