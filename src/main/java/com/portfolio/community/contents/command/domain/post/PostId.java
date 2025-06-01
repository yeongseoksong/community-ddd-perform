package com.portfolio.community.contents.command.domain.post;


import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class PostId {
    private Long postId;
}
