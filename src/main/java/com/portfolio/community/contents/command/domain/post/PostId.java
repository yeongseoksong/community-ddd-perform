package com.portfolio.community.contents.command.domain.post;


import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PostId {
    private Long value;

    public PostId(Long value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostId postId1 = (PostId) o;
        return Objects.equals(value, postId1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
