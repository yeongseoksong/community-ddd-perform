package com.portfolio.community.reaction.domain;

import com.portfolio.community.contents.command.domain.post.PostId;
import com.portfolio.community.member.command.domain.MemberId;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;



@DataJpaTest
class ReactionRepositoryTest {
    @Autowired
    private ReactionRepository reactionRepository;

    @Test
    public void existsByReactorIdAndTargetId는_정상동작한다(){
        ReactorId reactorId = new ReactorId(new MemberId());
        TargetId targetId = new TargetId(new PostId(1L));
        Reaction reaction = new Reaction(ReactionType.LIKE, reactorId, targetId);
        Reaction save = reactionRepository.save(reaction);

        boolean b = reactionRepository.existsByReactorIdAndTargetId(reactorId, targetId);

        Assertions.assertThat(b).isTrue();
    }

}