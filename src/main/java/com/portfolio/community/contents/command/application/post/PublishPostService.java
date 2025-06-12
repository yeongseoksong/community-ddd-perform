package com.portfolio.community.contents.command.application.post;


import com.portfolio.community.contents.command.application.category.GetCategoryService;
import com.portfolio.community.contents.command.domain.post.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@RequiredArgsConstructor
@Service
public class PublishPostService {
    private final GetPostService getPostService;
    private final GetCategoryService getCategoryService;

    public Post publishPost(PostId postId,  Author author,PostRequest postRequest) {
        getCategoryService.existById(postRequest.getCategoryId());
        Post post = getPostService.getByIdFromAuthor(author,postId);
        PostContent postContent = new PostContent(postRequest.getTitle(), postRequest.getContent());
        post.publishDraftPost(postContent, postRequest.getCategoryId(), postRequest.getIsPremium());
        return post;
    }
}
