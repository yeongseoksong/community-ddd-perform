package com.portfolio.community.resource.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@DiscriminatorValue("image")
public  class Image extends Resource {

    public Image(String path, String name,StorageType storageType) {
        super(path, name,storageType);
    }

}
