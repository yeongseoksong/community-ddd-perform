package com.portfolio.community.search;


import com.portfolio.community.common.response.Resp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Tag(name = "Search",description = "게시글 검색 API")
public class SearchQueryController {

    private final SearchMapper searchMapper;



    @Operation(summary = "게시글 검색", description = "keyword 로 게시글의 제목, 글내용, 작성자로 검색합니다.",
        parameters = {@Parameter(name = "keyword", description = "검색할 키워드",required = true, in = ParameterIn.QUERY)}
    )
    @GetMapping("/api/search")
    public Resp<List<SearchResultVO>> search(@RequestParam String keyword,
                                             @PageableDefault(page = 0,size=10) Pageable pageable){

        Pageable fixedPageable = PageRequest.of(
                pageable.getPageNumber(),
                10,
                pageable.getSort()
        );
        List<SearchResultVO> searchResultVOS = searchMapper.searchPosts(keyword,fixedPageable.getPageSize(),(int)fixedPageable.getOffset());
        return Resp.ok(searchResultVOS);
    }
}
