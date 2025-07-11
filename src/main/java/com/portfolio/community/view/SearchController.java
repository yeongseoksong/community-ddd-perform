package com.portfolio.community.view;

import com.portfolio.community.common.Pagination;
import com.portfolio.community.search.SearchMapper;
import com.portfolio.community.search.SearchResultVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SearchController {

    private final SearchMapper searchMapper;

    @GetMapping("/search")
    public String  search(
            @RequestParam String keyword,
            @PageableDefault(page = 0,size=10) Pageable pageable
            ,Model model) {

        Pageable fixedPageable = PageRequest.of(
                pageable.getPageNumber(),
                10,
                pageable.getSort()
        );
        List<SearchResultVO> searchResultVOS = searchMapper.searchPosts(keyword,fixedPageable.getPageSize(),(int)fixedPageable.getOffset());
        model.addAttribute("posts", searchResultVOS);


        long totalCount = searchMapper.countSearchPosts(keyword);
        Pagination pagination = new Pagination(totalCount, fixedPageable.getPageNumber());
        model.addAttribute("pagination", pagination);
        model.addAttribute("keyword", keyword);
        model.addAttribute("totalCount", totalCount);
        return "pages/search/search-list";
    }
}
