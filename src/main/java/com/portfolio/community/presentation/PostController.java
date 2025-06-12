package com.portfolio.community.presentation;

import com.portfolio.community.contents.command.application.category.GetCategoryService;
import com.portfolio.community.contents.command.domain.category.CategoryId;
import com.portfolio.community.contents.query.PostMapper;
import com.portfolio.community.contents.query.PostSummaryVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories/{categoryId}/posts")
@RequiredArgsConstructor
public class PostController {
    private final GetCategoryService categoryService;
    private final PostMapper postMapper;

    @GetMapping()
    public String postListPerCategory(@PathVariable String categoryId, Model model) {
        List<PostSummaryVO> posts = postMapper.getPostList();

        model.addAttribute("posts",posts);
        return "pages/post/list";
    }
//
    @GetMapping("/{postId}")
    public String postDetail(@PathVariable Long postId,Model model) {

        model.addAttribute("post");
        return "pages/post/detail";
    }

    @GetMapping("/write")
    public String createPost(@PathVariable String categoryId,Model model){


        return "pages/post/write";
    }

}
