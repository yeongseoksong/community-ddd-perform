package com.portfolio.community.contents.command.application.post;

import com.portfolio.community.contents.command.application.category.GetCategoryService;
import com.portfolio.community.contents.command.domain.post.Author;
import com.portfolio.community.contents.command.domain.post.Post;
import com.portfolio.community.contents.command.domain.post.PostContent;
import com.portfolio.community.contents.command.domain.post.PostId;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@RequiredArgsConstructor
@Service
public class EditPostService {
    private final GetPostService getPostService;
    private final GetCategoryService getCategoryService;

    public Post editPost(PostId postId, Author author, PostRequest postRequest) {
        getCategoryService.existById(postRequest.getCategoryId());
        Post post =getPostService.getByIdFromAuthor(author,postId);
        PostContent postContent = new PostContent(postRequest.getTitle(), postRequest.getContent());
        post.editPostContent(postContent, postRequest.getCategoryId(), postRequest.getIsPremium());
        return post;
    }
}
