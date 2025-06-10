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
    public DefaultResource(String path, String fileName, StorageType storageType) {
        super(path, fileName, storageType);
    }
}
