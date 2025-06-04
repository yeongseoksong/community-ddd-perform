package com.portfolio.community.contents.command.application;


import com.portfolio.community.contents.command.domain.post.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@RequiredArgsConstructor
@Service
public class PublishPostService {
    private final GetPostService getPostService;

    public Post publishPost(PostId postId, Author author, EditPostRequest editPostRequest) {
        Post post = getPostService.findById(postId);
        if(!post.getAuthor().equals(author)){
            throw new IllegalArgumentException("You are not allowed to publish this post");
        }
        PostContent postContent = new PostContent(editPostRequest.getTitle(), editPostRequest.getContent());
        post.publishDraftPost(postContent,editPostRequest.getCategoryId(),editPostRequest.getIsPremium());
        return post;
    }
}
