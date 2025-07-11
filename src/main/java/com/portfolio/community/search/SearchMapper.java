package com.portfolio.community.search;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SearchMapper {

    List<SearchResultVO> searchPosts(@Param("keyword") String keyword,
                                     @Param("pageSize") int pageSize,
                                     @Param("offset")  int offset);

    long countSearchPosts(@Param("keyword")String keyword);

}
