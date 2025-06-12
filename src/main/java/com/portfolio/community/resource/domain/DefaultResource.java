package com.portfolio.community.resource.domain;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@DiscriminatorValue("default")
public class DefaultResource  extends  Resource{
    public DefaultResource(String fileName, StorageType storageType,String contentType) {
        super(fileName, storageType,contentType);
    }

    public DefaultResource(String path, String fileName, StorageType storageType, String contentType) {
        super(path, fileName, storageType, contentType);
    }

    @Override
    protected boolean isSupportContentType(String contentType) {
         return contentType != null &&
                (contentType.equals("application/json") ||
                        contentType.equals("application/xml") ||
                        contentType.equals("text/html")||
                        contentType.equals("text/plain")||
                        contentType.equals("application/octet-stream"));
    }
}
