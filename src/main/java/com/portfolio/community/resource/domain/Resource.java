package com.portfolio.community.resource.domain;

import com.portfolio.community.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="resource_type")
@Table(name = "resource")
@Getter
public abstract  class Resource extends BaseEntity {
    @EmbeddedId
    private ResourceId id;

    @Enumerated(EnumType.STRING)
    private StorageType storageType;

    @Column(nullable = false)
    private String path;

    @Column(length = 30 ,nullable = false )
    private String fileName;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private  ResourceState state;


    public Resource(String path, String fileName,StorageType storageType) {
        if(path==null|| fileName ==null || storageType ==null)
            throw new IllegalArgumentException("Resource path, fileName, storageType  cannot be null");

        this.id= new ResourceId();
        this.path = path;
        this.fileName = fileName;
        this.state = ResourceState.SAVING;
        this.storageType=storageType;
    }


    public boolean verifyIsShow(){
        return this.state.isShow();
    }

    public void delete(){
        if(!this.state.equals(ResourceState.SAVING)) throw new IllegalStateException("Resource status is SAVING, can't delete resource ");
        this.state = ResourceState.DELETE;
    }
}
