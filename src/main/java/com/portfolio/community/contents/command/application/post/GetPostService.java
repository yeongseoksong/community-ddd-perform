package com.portfolio.community.contents.command.application.post;

import com.portfolio.community.contents.command.domain.post.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPostService {
    private final PostRepository postRepository;

    public Post getById(PostId id) {
        Post post = postRepository.findByIdAndStatusNot(id.getValue(),PostStatus.DELETED).orElseThrow(() -> new EntityNotFoundException("post not found"));

        return post;
    }

    public Post getByIdFromAuthor(Author author, PostId id) {
        Post post = getById(id);
        if(!post.getAuthor().equals(author)){
            throw new IllegalArgumentException("You are not allowed to publish this post");
        }
        return post;
    }


    public Post getByIdAndIsVisible(PostId id) {
        Post post = postRepository.findByIdAndStatusNot(id.getValue(), PostStatus.DELETED).orElseThrow(() -> new EntityNotFoundException("post not found"));
        if(!post.getStatus().isVisible())
            throw new PostVisibleException();
        return post;
    }
}
