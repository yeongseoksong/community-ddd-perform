package com.portfolio.community.contents.command.domain.category;

import com.portfolio.community.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Category extends BaseEntity {
    @EmbeddedId
    private CategoryId id;


    @Column(nullable = false, unique = true,length = 50)
    private String name;


    @Column(nullable = true)
    private String description;

    private Category(CategoryId id, String name,String description) {
        if(id==null||name==null) {
            throw new IllegalArgumentException("id and name can't be null");
        }

        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Category(String name) {
        this(new CategoryId(), name,null);
    }

    public static Category of(CategoryId id, String name) {
        return new Category(id, name,null);
    }
}
