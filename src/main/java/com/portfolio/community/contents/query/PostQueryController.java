package com.portfolio.community.contents.query;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostQueryController {

    private final PostMapper postMapper;

    @GetMapping("/{postId}")
    public PostDetailVO fetchPostDetail (@PathVariable Long postId){
        return postMapper.getPostWithResources(postId);
    }
}
