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
@Table(name = "post")
@Getter
public class Post extends BaseEntity {

    @Id
    @Column(name="post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Author author;

    @Embedded
    private PostContent postContent;

    @Embedded
    private CategoryId categoryId;

    private Long viewCount;

    private Long likeCount;

    private Long dislikeCount;

    @Enumerated(EnumType.STRING)
    private PostStatus status;

    private Boolean isPremium;

    public Post(Author author, PostContent postContent, CategoryId categoryId,Boolean isPremium) {
        if(author == null || postContent == null || categoryId == null) {
            throw new IllegalArgumentException("Post author and post content cannot be null");
        }
        this.author = author;
        setPostContent(postContent);
        this.categoryId = categoryId;
        this.likeCount=0L;
        this.dislikeCount=0L;
        this.viewCount = 0L;
        this.status = PostStatus.DRAFT;
        this.isPremium = isPremium;
    }

    private void setPostContent(PostContent postContent) {
        if(postContent == null)
            throw new IllegalArgumentException("Post content cannot be null");
        this.postContent = postContent;
    }

    void changePostStatus(PostStatus newStatus){
        if(newStatus == null)
            throw new IllegalArgumentException("Post status cannot be null");
        this.status = newStatus;
    }

    private void updatePost(PostContent postContent,CategoryId categoryId,Boolean isPremium){
        this.postContent = postContent != null ? postContent : this.postContent;
        this.categoryId = categoryId != null ? categoryId : this.categoryId;
        this.isPremium = isPremium != null ? isPremium : this.isPremium;
    }


    public void publishDraftPost(PostContent postContent,CategoryId categoryId,Boolean isPremium){
        if(this.status != PostStatus.DRAFT)
            throw new IllegalStateException("Post status is not DRAFT");

        this.changePostStatus(PostStatus.PUBLISHED);
        updatePost(postContent,categoryId,isPremium);
    }


    public void editPostContent(PostContent postContent,CategoryId categoryId,Boolean isPremium){
        this.status.handleStatusEdited(this);
        updatePost(postContent,categoryId,isPremium);
    }


    public void deletePost(){
        changePostStatus(PostStatus.DELETED);
    }

}
