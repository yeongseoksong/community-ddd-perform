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

    public Post publishPost(PostId postId,  PostRequest postRequest) {
        Post post = getPostService.findByIdFromAuthor(postRequest.getAuthor(),postId);
        PostContent postContent = new PostContent(postRequest.getTitle(), postRequest.getContent());
        post.publishDraftPost(postContent, postRequest.getCategoryId(), postRequest.getIsPremium());
        return post;
    }
}
