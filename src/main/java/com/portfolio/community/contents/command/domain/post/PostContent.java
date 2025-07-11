package com.portfolio.community.contents.command.domain.post;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class PostContent {

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    public PostContent(String title, String content) {
        if(title == null || content == null) {
            throw new IllegalArgumentException("Title and content must not be null");
        }
        if(title.length()>100){
            throw new IllegalArgumentException("Title must not exceed 100 characters");
        }

        this.title = title;
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostContent that = (PostContent) o;
        return Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getContent(), that.getContent());
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(getTitle());
        result = 31 * result + Objects.hashCode(getContent());
        return result;
    }
}
