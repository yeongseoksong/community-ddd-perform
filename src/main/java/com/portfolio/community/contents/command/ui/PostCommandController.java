package com.portfolio.community.contents.command.ui;


import com.portfolio.community.contents.command.application.CreatePostRequest;
import com.portfolio.community.contents.command.application.CreatePostService;
import com.portfolio.community.contents.command.application.EditPostRequest;
import com.portfolio.community.contents.command.application.PublishPostService;
import com.portfolio.community.contents.command.domain.post.Author;
import com.portfolio.community.contents.command.domain.post.Post;
import com.portfolio.community.contents.command.domain.post.PostId;
import com.portfolio.community.member.command.domain.MemberId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Tag(name="게시글 rest api",description = "게시글 rest api")
public class PostCommandController {

    private final CreatePostService createPostService;
    private final PublishPostService publishPostService;

    Author author = new Author(new MemberId(), "test");


    @PostMapping("/api/members/posts")
    @Operation(summary = "게시글 생성 api")
    public Post createPost(@RequestBody @Valid  CreatePostRequest createPostRequest) {



        createPostRequest.setAuthor(author);
        return createPostService.createPost(createPostRequest);
    }

    @PutMapping("/api/members/posts/{id}/publish")
    @Operation(summary = "게시글 게시 api")
    public Post publishPost(
            @PathVariable Long id,
            @RequestBody @Valid EditPostRequest editPostRequest) {


        PostId postId = new PostId(id);
        return publishPostService.publishPost(postId,author,editPostRequest);

    }
}
