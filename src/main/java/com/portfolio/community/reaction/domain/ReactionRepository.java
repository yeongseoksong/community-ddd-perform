package com.portfolio.community.reaction.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
  Optional<Reaction> findByReactorIdAndTargetId(ReactorId reactorId, TargetId targetId);

  boolean existsByReactorIdAndTargetId(ReactorId reactorId, TargetId targetId);
}
