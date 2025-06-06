package com.portfolio.community.contents.command.application.post;

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

    public Post editPost(PostId postId, PostRequest postRequest) {
        Post post =getPostService.findByIdFromAuthor(postRequest.getAuthor(),postId);
        PostContent postContent = new PostContent(postRequest.getTitle(), postRequest.getContent());
        post.editPostContent(postContent, postRequest.getCategoryId(), postRequest.getIsPremium());
        return post;
    }
}
