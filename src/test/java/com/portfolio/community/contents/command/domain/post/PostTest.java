package com.portfolio.community.contents.command.domain.post;


import com.portfolio.community.contents.PostFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PostTest {
    private final String authorId ="random uuid";
    private final String authorName ="random author name";
    private final String title = "random title";
    private final String content = "random content";
    private final String categoryId = "random category id";


    @Test
    public void DRAFT_게시글_수정은_상태를_DRAFT로_유지한다(){
        Post post = PostFactory.generate(authorId, authorName, title, content, categoryId);
        PostContent newPostContent = new PostContent(title,"content2");
        post.editPostContent(newPostContent,post.getCategoryId(),post.getIsPremium());
        assertThat(post.getPostContent()).isEqualTo(newPostContent);
        assertThat(post.getStatus()).isEqualTo(PostStatus.DRAFT);
    }

    @Test
    public void PUBLISHED_게시글_수정은_상태를_EDITED로_변경한다(){
        Post post = PostFactory.generate(authorId, authorName, title, content, categoryId);
        post.publishDraftPost(post.getPostContent(),post.getCategoryId(),post.getIsPremium());
        PostContent newPostContent = new PostContent(title,"content2");
        post.editPostContent(newPostContent,post.getCategoryId(),post.getIsPremium());
        assertThat(post.getPostContent()).isEqualTo(newPostContent);
        assertThat(post.getStatus()).isEqualTo(PostStatus.EDITED);
    }

    @Test
    public void PUBLISH와_게시글_수정을_동시에_할_수_있다(){
        Post post = PostFactory.generate(authorId, authorName, title, content, categoryId);
        PostContent newPostContent = new PostContent(title,"content2");

        post.publishDraftPost(newPostContent,post.getCategoryId(),post.getIsPremium());
        assertThat(post.getPostContent()).isEqualTo(newPostContent);
        assertThat(post.getStatus()).isEqualTo(PostStatus.PUBLISHED);
    }

    @Test
    public void 인기_게시글_수정은_불가능하다(){
        Post post = PostFactory.generate(authorId, authorName, title, content, categoryId);
        post.changePostStatus(PostStatus.HOT);
        PostContent newPostContent = new PostContent(title,"content2");
        assertThatThrownBy(()->
                post.editPostContent(newPostContent,post.getCategoryId(),post.getIsPremium())).isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void DELETED_게시글_수정은_불가능하다(){
        Post post = PostFactory.generate(authorId, authorName, title, content, categoryId);
        post.deletePost();
        PostContent newPostContent = new PostContent(title,"content2");
        assertThatThrownBy(()->
                post.editPostContent(newPostContent,post.getCategoryId(),post.getIsPremium())).isInstanceOf(IllegalStateException.class);
    }

}