package com.portfolio.community.contents.command.application;

import com.portfolio.community.contents.command.application.post.CreatePostRequest;
import com.portfolio.community.contents.command.application.post.CreatePostService;
import com.portfolio.community.contents.command.domain.category.Category;
import com.portfolio.community.contents.command.domain.category.CategoryId;
import com.portfolio.community.contents.command.domain.category.CategoryRepository;
import com.portfolio.community.contents.command.domain.post.*;
import com.portfolio.community.member.command.domain.MemberId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CreatePostServiceTest {

    @MockBean
    private AuthorService authorService;

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private CreatePostService createPostService;

    @Test
    public void createPostPost() {
        MemberId memberId = new MemberId();;
        Author author = new Author(memberId,"Test");
        when(authorService.createAuthor(memberId)).thenReturn(author);

        Category free = new Category( "free");
        when(categoryRepository.findById(free.getId())).thenReturn(Optional.of(free));


        CreatePostRequest createDefaultPost = new CreatePostRequest(author,"post", "content", false, free.getId());
        Post defaultPost = createPostService.createPost( createDefaultPost);


        assertThat(defaultPost).isInstanceOf(Post.class);
        assertThat(defaultPost.getCategoryId()).isEqualTo(free.getId());
        assertThat(defaultPost.getPostContent().getContent()).isEqualTo("content");
        assertThat(defaultPost.getPostContent().getTitle()).isEqualTo("post");
        assertThat(defaultPost.getAuthor()).isEqualTo(author);
    }

}