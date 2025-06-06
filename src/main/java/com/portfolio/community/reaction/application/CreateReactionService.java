package com.portfolio.community.reaction.application;

import com.portfolio.community.contents.command.application.post.GetPostService;
import com.portfolio.community.contents.command.domain.post.Post;
import com.portfolio.community.contents.command.domain.post.PostRepository;
import com.portfolio.community.reaction.domain.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateReactionService {

    private final ReactionRepository reactionRepository;
    private final GetPostService getPostService;
    // 좋아요시 post 수정 트랜잭션과 lock 경쟁 발생 ! 성능 개선할 것
    public Reaction createReaction(ReactorId reactorId, TargetId targetId, ReactionType reactionType) {
        Post post = getPostService.findByIdAndIsVisible(targetId.getPostId());
        boolean b = reactionRepository.existsByReactorIdAndTargetId(reactorId, targetId);
        if(b) throw new AlreadyReactionException();

        if(reactionType.equals(ReactionType.Like))
            post.incrementLikeCount();
        else
            post.incrementDislikeCount();

        return reactionRepository.save(new Reaction(reactionType,reactorId,targetId));
    };

}
