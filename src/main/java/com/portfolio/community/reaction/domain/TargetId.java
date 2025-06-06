package com.portfolio.community.reaction.domain;


import com.portfolio.community.contents.command.domain.post.PostId;
import com.portfolio.community.member.command.domain.MemberId;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TargetId {

    @AttributeOverride(name="value",column = @Column(name="target_id"))
    private PostId postId;

    public TargetId(PostId postId) {
        this.postId = postId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TargetId targetId = (TargetId) o;
        return Objects.equals(getPostId(), targetId.getPostId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getPostId());
    }
}
