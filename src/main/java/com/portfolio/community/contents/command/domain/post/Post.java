package com.portfolio.community.contents.command.domain.post;

import com.portfolio.community.common.entity.BaseEntity;
import com.portfolio.community.contents.command.domain.category.CategoryId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
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


    @Enumerated(EnumType.STRING)
    private PostStatus prevStatus;

    private Boolean isPremium;

    public Post(Author author,CategoryId categoryId) {
        if(author==null || categoryId==null)
            throw new IllegalArgumentException("Post author, categoryId  cannot be null");
        this.author =author;
        this.categoryId=categoryId;
        this.status=PostStatus.DRAFT;
        this.viewCount=0L;
        this.likeCount=0L;
        this.dislikeCount=0L;
    }

    public Post(Author author, PostContent postContent, CategoryId categoryId, Boolean isPremium) {
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
        this.prevStatus=null;
    }

    public PostId getId(){
        return new PostId(this.id);
    }
    private void setPostContent(PostContent postContent) {
        if(postContent == null)
            throw new IllegalArgumentException("Post content cannot be null");
        this.postContent = postContent;
    }

    void changePostStatus(PostStatus newStatus){
        if(newStatus == null)
            throw new IllegalArgumentException("Post status cannot be null");

        if(this.status != newStatus)
            this.prevStatus=this.status;
        this.status = newStatus;
    }

     void rollbackPostStatus(){
        changePostStatus(this.prevStatus);
    }


    private void updatePost(PostContent postContent,CategoryId categoryId,Boolean isPremium){
        this.postContent = postContent != null ? postContent : this.postContent;
        this.categoryId = categoryId != null ? categoryId : this.categoryId;
        this.isPremium = isPremium != null ? isPremium : this.isPremium;
    }


    public void publishDraftPost(){
        if(this.status != PostStatus.DRAFT)
            throw new IllegalStateException("Post status is not DRAFT");

        this.changePostStatus(PostStatus.PUBLISHED);
    }


    public void editPostContent(PostContent postContent,CategoryId categoryId,Boolean isPremium){
        this.status.handleStatusEdited(this);
        updatePost(postContent,categoryId,isPremium);
    }


    public void incrementLikeCount(){
        this.likeCount++;
        if(verifyIsHotPost())
            this.changePostStatus(PostStatus.HOT);
    }

    public void incrementDislikeCount(){
        this.dislikeCount++;
        if(!verifyIsHotPost())
            this.rollbackPostStatus();
    }



    private boolean verifyIsHotPost(){
        final Long HOT_POST_THRESHOLD = 20L;
        return likeCount-dislikeCount >= HOT_POST_THRESHOLD;
    }

    public void deletePost(){
        changePostStatus(PostStatus.DELETED);
    }

}
