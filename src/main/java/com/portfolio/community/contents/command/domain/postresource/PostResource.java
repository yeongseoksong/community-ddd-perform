package com.portfolio.community.contents.command.domain.postresource;

import com.portfolio.community.contents.command.domain.post.PostId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PostResource {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "post_resource_id")
    private Long id;

    public PostResource(PostId postId, ResourceTarget resourceTarget) {
        if(postId==null || resourceTarget ==null)
            throw new IllegalArgumentException("PostResource postId,resourceTarget cannot be null");
        this.postId = postId;
        this.resourceTarget = resourceTarget;
        this.state=PostResourceState.ACTIVE;
    }

    @Embedded
    private PostId postId;

    @Embedded
    private ResourceTarget resourceTarget;


    @Enumerated(EnumType.STRING)
    private PostResourceState state;

    public void delete(){
        if(this.state.equals(PostResourceState.ACTIVE))
            this.state = PostResourceState.DELETED;
    }

}
