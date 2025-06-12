package com.portfolio.community.contents.command.domain.post;


import com.portfolio.community.contents.PostFactory;
import com.portfolio.community.contents.command.domain.category.CategoryId;
import com.portfolio.community.member.command.domain.MemberId;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PostTest {
    private final String authorId ="random uuid";
    private final String authorName ="random author name";
    private final String title = "random title";
    private final String content = "random content";
    private final String categoryId = "random category id";

    @Test
    public void 게시글_생성은_일부정보로_가능하며_상태는_DRAFT(){
        Author author = new Author(MemberId.of(authorId),authorName);
        CategoryId categoryId_ = new CategoryId();
        Post post = new Post(author, categoryId_);
        assertThat(post.getAuthor()).isEqualTo(author);
        assertThat(post.getCategoryId()).isEqualTo(categoryId_);
        assertThat(post.getPostContent()).isNull();
        assertThat(post.getDislikeCount()).isEqualTo(0);
        assertThat(post.getLikeCount()).isEqualTo(0);
    }

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
        post.publishDraftPost();
        PostContent newPostContent = new PostContent(title,"content2");
        post.editPostContent(newPostContent,post.getCategoryId(),post.getIsPremium());
        assertThat(post.getPostContent()).isEqualTo(newPostContent);
        assertThat(post.getStatus()).isEqualTo(PostStatus.EDITED);
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

    @Test
    public void 게시글_상태가_변경되면_이전_게시글_상태를_저장한다(){
        Post post = PostFactory.generate(authorId, authorName, title, content, categoryId);
        post.changePostStatus(PostStatus.HOT);
        assertThat(post.getStatus()).isEqualTo(PostStatus.HOT);
        assertThat(post.getPrevStatus()).isEqualTo(PostStatus.DRAFT);
    }

    @Test
    public void 게시글_좋아요수가_기준치를_넘어서면_HOT_게시글로_변경된다(){
        Post post = PostFactory.generate(authorId, authorName, title, content, categoryId);
        assertThat(post.getStatus()).isEqualTo(PostStatus.DRAFT);

        for(int i=0; i<20; i++){
            post.incrementLikeCount();
        }
        assertThat(post.getStatus()).isEqualTo(PostStatus.HOT);
    }


    @Test
    public void 게시글_상태는_되돌릴_수_있다(){
        Post post = PostFactory.generate(authorId, authorName, title, content, categoryId);
        post.changePostStatus(PostStatus.HOT);
        post.rollbackPostStatus();
        assertThat(post.getStatus()).isEqualTo(PostStatus.DRAFT);
    }
}