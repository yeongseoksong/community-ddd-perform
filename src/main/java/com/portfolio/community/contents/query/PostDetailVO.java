package com.portfolio.community.contents.query;


import lombok.Getter;
import java.util.List;
import java.time.LocalDateTime;

@Getter
public class PostDetailVO {
    Long postId;
    Long dislikeCount;
    Long likeCount;
    Boolean isPremium;
    LocalDateTime updatedAt;
    Long viewCount;
    String title;
    String content;
    String categoryId;
    String authorName;
    String status;
    List<AttachmentSummaryVo> attachments;
}
