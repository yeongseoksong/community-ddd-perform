package com.portfolio.community.reaction.presentation;


import com.portfolio.community.common.response.Resp;
import com.portfolio.community.contents.command.domain.post.Author;
import com.portfolio.community.contents.command.domain.post.Post;
import com.portfolio.community.contents.command.domain.post.PostId;
import com.portfolio.community.member.command.domain.MemberId;
import com.portfolio.community.reaction.application.CreateReactionService;
import com.portfolio.community.reaction.application.GetReactionService;
import com.portfolio.community.reaction.domain.Reaction;
import com.portfolio.community.reaction.domain.ReactionType;
import com.portfolio.community.reaction.domain.ReactorId;
import com.portfolio.community.reaction.domain.TargetId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Reaction", description = "게시물 반응 관련 API")
public class ReactionController {
    private final CreateReactionService createReactionService;
    private final GetReactionService getReactionService;


    final Author author = new Author(MemberId.of("test"), "test");

    @Operation(summary = "게시글에 리액션 추가", description = "게시물 ID 에 해당하는 게시물에 리액션(좋아요, 싫어요)을 추가합니다.",
            parameters = {
                    @Parameter(name = "postId", description = "게시물 ID", required = true, in = ParameterIn.PATH),
                    @Parameter(name = "type", description = "리액션 타입 (LIKE, DISLIKE 등)", required = true, in = ParameterIn.QUERY)
            })
    @PostMapping("/api/member/posts/{postId}/reaction")
    public Resp<Reaction> createReaction(
                                            @PathVariable Long postId,
                                             @RequestParam("type")  ReactionType reactionType) {

        ReactorId reactorId = new ReactorId(author.getMemberId()); //임시
        TargetId targetId = new TargetId(new PostId(postId));

        return Resp.ok(createReactionService.createReaction(reactorId,targetId,reactionType));
    }

//    @Operation(summary = "리액션 조회",description = "게시물 ID 에 해당하는 게시물에 내가 추가한 리액션 정보를 조회합니다.",
//    parameters = {
//            @Parameter(name="postId",description = "게시물 ID", required = true, in=ParameterIn.PATH),
//    })
//    @GetMapping("/api/member/posts/{postId}/reaction")
//    public Resp<Reaction> fetchMyReaction(@PathVariable Long postId){
//        ReactorId reactorId = new ReactorId(author.getMemberId());
//
//        TargetId targetId = new TargetId(new PostId(postId));
//        return Resp.ok(getReactionService.findMyReactionOnPost(targetId,reactorId));
//    }
}
