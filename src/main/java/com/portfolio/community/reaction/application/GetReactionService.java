package com.portfolio.community.reaction.application;


import com.portfolio.community.contents.command.domain.post.PostId;
import com.portfolio.community.reaction.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetReactionService {
    private final ReactionRepository reactionRepository;



    public Optional<Reaction> findMyReactionOnPost(TargetId targetId, ReactorId reactorId){

        return reactionRepository.findByReactorIdAndTargetId(reactorId, targetId);
    }
}
