package com.portfolio.community.contents.query;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostSummaryVO {
    Long postId;
    LocalDateTime updatedAt;
    Long likeCount;
    Long viewCount;
    String title;
    String categoryName;
    String categoryId;
    String authorName;
}
