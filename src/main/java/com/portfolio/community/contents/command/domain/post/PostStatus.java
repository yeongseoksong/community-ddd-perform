package com.portfolio.community.contents.command.domain.post;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PostStatus {
    DRAFT(false, true) {
        @Override
        public void handleStatusEdited(Post post) {
            if(!isEditable()) throw new IllegalStateException("Can't edit post status");
            post.changePostStatus(DRAFT);
        }
    },

    PUBLISHED(true, true) {
        @Override
        public void handleStatusEdited(Post post) {
            if(!isEditable()) throw new IllegalStateException("Can't edit post status");
            post.changePostStatus(EDITED);
        }
    },

    DELETED(false, false) {
        @Override
        public void handleStatusEdited(Post post) {
            if(!isEditable()) throw new IllegalStateException("Can't edit post status");
        }
    },

    EDITED(true, true) {
        @Override
        public void handleStatusEdited(Post post) {
            if(!isEditable()) throw new IllegalStateException("Can't edit post status");
            post.changePostStatus(EDITED);
        }
    },

    HOT(true,false){
        @Override
        public void handleStatusEdited(Post post) {
            if(!isEditable()) throw new IllegalStateException("HOT status post can't be edited");
        }
    };



    private final boolean visible;
    private final boolean editable;
    public abstract void handleStatusEdited(Post post);

}

