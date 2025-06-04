package com.portfolio.community.contents.command.application.post;

import com.portfolio.community.contents.command.domain.post.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetPostService {
    private final PostRepository postRepository;

    public Post findById(PostId id) {
        Post post = postRepository.findByIdAndStatusNot(id.getValue(),PostStatus.DELETED).orElseThrow(() -> new EntityNotFoundException("post not found"));

        return post;
    }

    public Post findByIdAndIsVisible(PostId id) {
        Post post = postRepository.findByIdAndStatusNot(id.getValue(), PostStatus.DELETED).orElseThrow(() -> new EntityNotFoundException("post not found"));
        if(!post.getStatus().isVisible())
            throw new PostVisibleException();
        return post;
    }
}
