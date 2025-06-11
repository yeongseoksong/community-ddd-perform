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


    private String path;


    private String fileName;


    private String url;


    private String contentType;


    @Enumerated(EnumType.STRING)
    private  ResourceState state;


    public Resource(String path, String fileName,StorageType storageType,String contentType) {
        if(path==null|| fileName ==null || storageType ==null || contentType ==null)
            throw new IllegalArgumentException("Resource path, fileName, storageType, contentType   cannot be null");

        this.id= new ResourceId();
        this.path = path;
        this.fileName = fileName;
        this.state = ResourceState.SAVING;
        this.storageType=storageType;
        this.url= genUrl();
        setContentType(contentType);
    }

    private void setContentType(String contentType) {
        if(!isSupportContentType(contentType))
            throw new IllegalArgumentException("Content type not supported");
        this.contentType=contentType;
    }

    protected abstract boolean isSupportContentType(String contentType);

    private String genUrl() {
        return this.storageType.getBaseUrl() +"/"+ this.id.getValue();
    }


    public boolean verifyIsShow(){
        return this.state.isShow();
    }

    public void delete(){
        if(!this.state.equals(ResourceState.SAVING)) throw new IllegalStateException("Resource status is SAVING, can't delete resource ");
        this.state = ResourceState.DELETE;
    }

    public void setStateActive() {
        if(!this.state.equals(ResourceState.SAVING)) throw new IllegalStateException("Resource status is SAVING");
        this.state=ResourceState.ACTIVE;
    }
}
