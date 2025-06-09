package com.portfolio.community.resource.domain;

import com.portfolio.community.common.entity.BaseEntity;
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

    @Enumerated(EnumType.STRING)
    StorageType storageType;

    @Column(nullable = false)
    private String path;

    @Column(length = 30 ,nullable = false )
    private String fileName;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private  ResourceState state;


    public Resource(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
        this.state = ResourceState.SAVING;
    }


    public boolean verifyIsShow(){
        return this.state.isShow();
    }
}
