package com.portfolio.community.contents.query;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {
    PostDetailVO findPostWithResources(Long postId);
}
