package com.portfolio.community.contents.command.domain.post;

import com.portfolio.community.contents.command.domain.category.CategoryId;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("premium")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PremiumPost extends Post {
    public PremiumPost(Author author, PostContent content, CategoryId categoryId) {
        super(author, content, categoryId);
    }
}
