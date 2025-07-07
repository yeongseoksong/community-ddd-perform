package com.portfolio.community.reaction.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
public class Reaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reaction_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReactionType reactionType;

    @Embedded
    private ReactorId reactorId;

    @Embedded
    private TargetId targetId;

    public Reaction(ReactionType reactionType, ReactorId reactorId, TargetId targetId) {
        this.reactionType = reactionType;
        this.reactorId = reactorId;
        this.targetId = targetId;
    }
}
