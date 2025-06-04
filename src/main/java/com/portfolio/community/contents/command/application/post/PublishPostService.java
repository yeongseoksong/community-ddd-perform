package com.portfolio.community.contents.command.application.post;


import com.portfolio.community.contents.command.domain.post.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@RequiredArgsConstructor
@Service
public class PublishPostService {
    private final GetPostService getPostService;

    public Post publishPost(PostId postId,  CreatePostRequest createPostRequest) {
        Post post = getPostService.findById(postId);
        if(!post.getAuthor().equals(createPostRequest.getAuthor())){
            throw new IllegalArgumentException("You are not allowed to publish this post");
        }
        PostContent postContent = new PostContent(createPostRequest.getTitle(), createPostRequest.getContent());
        post.publishDraftPost(postContent,createPostRequest.getCategoryId(),createPostRequest.getIsPremium());
        return post;
    }
}
