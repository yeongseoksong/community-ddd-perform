package com.portfolio.community.contents.query;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    PostDetailVO getPostWithResources(Long postId);

    List<PostSummaryVO> getPostList();

    List<PostSummaryVO> getPostListByCategoryId(@Param("categoryId") String categoryId,
                                                @Param("pageSize") int pageSize,
                                                @Param("offset")  int offset,
                                                @Param("sortField") String sortField,
                                                @Param("sortDirection") String sortDirection);

    long countPostsByCategoryId(String categoryId);
}
