package com.portfolio.community.contents.command.domain.post;

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
public class Author {

    @AttributeOverride(name="value",column = @Column(name="author_id"))
    private MemberId memberId;

    @Column(name="author_name",nullable = false)
    private String name;

    public Author(MemberId memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;
        return Objects.equals(memberId, author.memberId)
                && Objects.equals(name, author.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, name);
    }
}
