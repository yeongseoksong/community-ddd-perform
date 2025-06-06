package com.portfolio.community.reaction.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {


  boolean existsByReactorIdAndTargetId(ReactorId reactorId, TargetId targetId);
}
