package com.portfolio.community.contents.query;

import com.portfolio.community.common.response.Resp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories/{categoryId}/posts")
public class PostQueryController {
    private final PostQueryService postQueryService;

    @GetMapping()
    public Resp<List<PostSummaryVO>> postListPerCategory(@PathVariable String categoryId,
                                      @PageableDefault(page = 0,size=10
                                      ) Pageable pageable, Model model) {


        Pageable fixedPageable = PageRequest.of(
                pageable.getPageNumber(),
                10,
                pageable.getSort()
        );
        List<PostSummaryVO> posts = postQueryService.fetchPostsByCategory(categoryId,fixedPageable);

        return Resp.ok(posts);
    }
}
