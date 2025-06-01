package com.portfolio.community.contents.command.domain.post;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Embeddable
public class PostContent {

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String content;
}
