package com.portfolio.community.presentation;

import com.portfolio.community.contents.command.application.category.GetCategoryService;
import com.portfolio.community.contents.command.application.post.CreatePostService;
import com.portfolio.community.contents.command.application.post.GetPostService;
import com.portfolio.community.contents.command.domain.category.Category;
import com.portfolio.community.contents.command.domain.category.CategoryId;
import com.portfolio.community.contents.command.domain.post.Author;
import com.portfolio.community.contents.command.domain.post.Post;
import com.portfolio.community.contents.command.domain.post.PostId;
import com.portfolio.community.contents.query.PostDetailVO;
import com.portfolio.community.contents.query.PostMapper;
import com.portfolio.community.contents.query.PostQueryService;
import com.portfolio.community.contents.query.PostSummaryVO;
import com.portfolio.community.member.command.domain.MemberId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/categories/{categoryId}/posts")
@RequiredArgsConstructor
public class PostController {
    private final GetCategoryService getCategoryService;
    private final CreatePostService createPostService;
    private final GetPostService getPostService;
    private final PostMapper postMapper;

    private final PostQueryService postQueryService;

    final Author author = new Author(MemberId.of("test"), "test");



    @GetMapping()
    public String postListPerCategory(

            @PathVariable String categoryId,
                                      @PageableDefault(page = 0,size=PostQueryService.pageSize
                                      ) Pageable pageable, Model model) {

        Pageable fixedPageable = PageRequest.of(
                pageable.getPageNumber(),
                PostQueryService.pageSize,
                pageable.getSort()
        );

        Category category = getCategoryService.getById(new CategoryId(categoryId));

        Map<String, Object> posts = postQueryService.fetchPostsByCategoryWithPage(categoryId,fixedPageable);


        model.addAttribute("posts",posts.get("posts"));
        model.addAttribute("categoryName",category.getName());
        model.addAttribute("categoryDesc",category.getDescription());
        model.addAttribute("pagination",posts.get("pagination"));


        return "pages/post/list";
    }


    @GetMapping("/{postId}")
    public String postDetail(@PathVariable Long postId, Model model) {
        PostDetailVO postWithResources = postMapper.getPostWithResources(postId);
        model.addAttribute("post",postWithResources);
        return "pages/post/detail";
    }


    @GetMapping("/init")
    public String createPost(@PathVariable String categoryId){
        Post initialPost = createPostService.createInitialPost(author, new CategoryId(categoryId));
        return "redirect:/categories/"+categoryId+"/posts/"+initialPost.getId().getValue()+"/edit";
    }


    @GetMapping("/{postId}/edit")
    public String editPost(@PathVariable String categoryId,@PathVariable Long postId, Model model){
        getCategoryService.assertById(new CategoryId(categoryId));
        model.addAttribute("post",getPostService.getByIdFromAuthor(author,new PostId(postId)));
        return "pages/post/edit";
    }

}
