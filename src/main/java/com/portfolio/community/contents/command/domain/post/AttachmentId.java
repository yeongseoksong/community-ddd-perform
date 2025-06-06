package com.portfolio.community.contents.command.domain.post;

import com.portfolio.community.resource.domain.ResourceId;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;


@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttachmentId {

    private String id;

    public AttachmentId(ResourceId resourceId) {
        this.id = resourceId.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttachmentId that = (AttachmentId) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
