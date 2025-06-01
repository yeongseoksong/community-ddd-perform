package com.portfolio.community.contents.command.domain.post;

import com.portfolio.community.common.jpa.BaseEntity;
import com.portfolio.community.contents.command.domain.category.CategoryId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="post_type")
@Table(name = "post")
@Getter
public abstract class Post extends BaseEntity {

    @Id
    @Column(name="post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Author author;

    @Embedded
    private PostContent postContent;

    private CategoryId categoryId;
//
    private Long viewCount;
//
//    private Long likeCount;
//
//    private Long dislikeCount;

    private PostStatus status;

    public Post(Author author, PostContent postContent, CategoryId categoryId) {
        this.author = author;
        this.postContent = postContent;
        this.categoryId = categoryId;
        this.viewCount = 0L;
    }
}
