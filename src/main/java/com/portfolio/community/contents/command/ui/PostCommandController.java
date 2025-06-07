package com.portfolio.community.contents.command.ui;


import com.portfolio.community.common.response.Resp;
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

    Author author = new Author(MemberId.of("test"), "test");


    @PostMapping("/api/members/posts")
    @Operation(summary = "게시글 생성 api")
    public Resp<Post> createPost(@RequestBody @Valid PostRequest postRequest) {

        return Resp.ok(createPostService.createPost(author,postRequest));
    }

    @PutMapping("/api/members/posts/{id}/publish")
    @Operation(summary = "게시글 공개 api")
    public Resp<Post> publishPost(
            @PathVariable Long id,
            @RequestBody @Valid PostRequest postRequest) {



        PostId postId = new PostId(id);
        return Resp.ok(publishPostService.publishPost(postId,author, postRequest));
    }

    @PutMapping("/api/members/posts/{id}")
    @Operation(summary = "게시글 수정 api")
    public Resp<Post> editPost(
            @PathVariable Long id,
            @RequestBody @Valid PostRequest postRequest) {



        PostId postId = new PostId(id);
        return Resp.ok(editPostService.editPost(postId, author,postRequest));
    }

    @DeleteMapping("/api/members/posts/{id}")
    @Operation(summary = "게시글 삭제 api")
    public  Resp<Void> deletePost(@PathVariable Long id) {
        PostId postId = new PostId(id);
        deletePostService.deletePostByIdFromAuthor(author,postId);
        return Resp.ok();
    }
}

