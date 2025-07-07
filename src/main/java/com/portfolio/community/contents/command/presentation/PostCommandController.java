package com.portfolio.community.contents.command.presentation;


import com.portfolio.community.common.response.Resp;
import com.portfolio.community.contents.command.application.post.*;
import com.portfolio.community.contents.command.domain.category.CategoryId;
import com.portfolio.community.contents.command.domain.post.Author;
import com.portfolio.community.contents.command.domain.post.Post;
import com.portfolio.community.contents.command.domain.post.PostId;
import com.portfolio.community.member.command.domain.MemberId;
import com.portfolio.community.resource.domain.Resource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Tag(name="게시글 rest api",description = "게시글 rest api")
public class PostCommandController {

    private final CreatePostService createPostService;
    private final PublishPostService publishPostService;
    private final EditPostService editPostService;
    private final DeletePostService deletePostService;
    private final PersistPostResourceService persistPostResourceService;


    final Author author = new Author(MemberId.of("test"), "test");

    @PostMapping(value = "/api/members/categories/{categoryId}/posts")
    @Operation(summary = "게시글 초기 api")
    public Resp<Post> createInitialPost(@PathVariable String  categoryId) {

        CategoryId categoryId_ = CategoryId.of(categoryId);
        return Resp.ok(createPostService.createInitialPost(author,categoryId_));
    }



    @PostMapping(value = "/api/members/posts/{id}/resources")
    @Operation(summary = "게시글 하위 단일 리소스 저장 api")
    public Resp<Resource> persistPostResources(
            @PathVariable Long id,
            MultipartFile upload) {
        return Resp.ok(persistPostResourceService.validatePostAndPersistImage(author,new PostId(id)
                ,upload));
    }


    @PutMapping("/api/members/posts/{id}/publish")
    @Operation(summary = "게시글 공개 api")
    public Resp<Post> publishPost(
            @PathVariable Long id) {
        PostId postId = new PostId(id);
        return Resp.ok(publishPostService.publishPost(postId,author));
    }


    @PutMapping(value = "/api/members/posts/{postId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "게시글 편집 api")
    public Resp<Post> editPost(
            @PathVariable Long postId,
            @RequestPart(value="post-payload") @Valid PostRequest postPayload,
            @RequestPart(value="attachments",required = false) List<MultipartFile> attachments) {

        PostId postId_ = new PostId(postId);
        return Resp.ok(editPostService.editPost(postId_, author,postPayload,attachments));
    }


    @DeleteMapping("/api/members/posts/{id}")
    @Operation(summary = "게시글 삭제 api")
    public  Resp<Void> deletePost(@PathVariable Long id) {
        PostId postId = new PostId(id);
        deletePostService.deletePostByIdFromAuthor(author,postId);
        return Resp.ok();
    }
}

