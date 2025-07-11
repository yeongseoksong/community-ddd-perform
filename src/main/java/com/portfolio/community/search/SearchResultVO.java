package com.portfolio.community.search;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SearchResultVO {
    Long postId;
    LocalDateTime updatedAt;
    Long likeCount;
    Long viewCount;
    String title;
    String categoryName;
    String categoryId;
    String authorName;
    Boolean isPremium;

}
