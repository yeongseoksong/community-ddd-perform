package com.portfolio.community.contents.command.domain.category;

import com.portfolio.community.common.jpa.BaseEntity;
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

    public Category(CategoryId id, String name) {
        this.id = id;
        this.name = name;
    }
}
