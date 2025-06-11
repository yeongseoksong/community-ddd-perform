package com.portfolio.community.contents.query;


import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface PostMapper {
    PostDetailVO getPostWithResources(Long postId);

    List<PostSummaryVO> getPostList();
}
