package com.portfolio.community.contents.command.domain.post;

import com.portfolio.community.contents.command.domain.category.CategoryId;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("default")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DefaultPost extends Post {
    public DefaultPost(Author author, PostContent content, CategoryId categoryId) {
        super(author, content, categoryId);
    }
}
