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


    public Post publishPost(PostId postId,  Author author) {

        Post post = getPostService.getByIdFromAuthor(author,postId);
        post.publishDraftPost();
        return post;
    }
}
