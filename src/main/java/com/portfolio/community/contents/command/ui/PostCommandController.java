package com.portfolio.community.contents.command.ui;


import com.portfolio.community.contents.command.application.post.*;
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
    private final EditPostService editPostService;
    private final DeletePostService deletePostService;

    Author author = new Author(new MemberId(), "test");


    @PostMapping("/api/members/posts")
    @Operation(summary = "게시글 생성 api")
    public Post createPost(@RequestBody @Valid PostRequest postRequest) {

        postRequest.setAuthor(author);
        return createPostService.createPost(postRequest);
    }

    @PutMapping("/api/members/posts/{id}/publish")
    @Operation(summary = "게시글 공개 api")
    public Post publishPost(
            @PathVariable Long id,
            @RequestBody @Valid PostRequest postRequest) {

        postRequest.setAuthor(author);

        PostId postId = new PostId(id);
        return publishPostService.publishPost(postId, postRequest);
    }

    @PutMapping("/api/members/posts/{id}")
    @Operation(summary = "게시글 수정 api")
    public Post editPost(
            @PathVariable Long id,
            @RequestBody @Valid PostRequest postRequest) {

        postRequest.setAuthor(author);

        PostId postId = new PostId(id);
        return editPostService.editPost(postId, postRequest);
    }

    @DeleteMapping("/api/members/posts/{id}")
    @Operation(summary = "게시글 삭제 api")
    public  void deletePost(@PathVariable Long id) {
        PostId postId = new PostId(id);
        deletePostService.deletePostByIdFromAuthor(author,postId);
    }
}

