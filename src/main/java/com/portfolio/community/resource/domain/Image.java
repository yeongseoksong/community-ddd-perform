package com.portfolio.community.resource.domain;


import jakarta.persistence.*;
import jdk.jfr.ContentType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@DiscriminatorValue("image")
public  class Image extends Resource {

    public Image( String name, StorageType storageType, String contentType
    ) {
        super( name,storageType,contentType);
    }


    public Image(String path, String fileName, StorageType storageType, String contentType) {
        super(path, fileName, storageType, contentType);
    }

    @Override
    protected boolean isSupportContentType(String contentType) {
            return contentType != null &&
                    (contentType.equals("image/png")
                            || contentType.equals("image/jpeg")
                            || contentType.equals("image/jpg"))
                            || contentType.equals("image/gif");
    }
}
