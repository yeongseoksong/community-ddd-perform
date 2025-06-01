package com.portfolio.community.contents.command.application;

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
    public void createPost() {
        MemberId memberId = new MemberId();;
        Author author = new Author(memberId,"Test");
        when(authorService.createAuthor(memberId)).thenReturn(author);

        String categoryIdStr = "free";
        CategoryId categoryId = new CategoryId(categoryIdStr);
        Category free = new Category(categoryId, "free-category");
        when(categoryRepository.findById(free.getId())).thenReturn(Optional.of(free));


        CreatePostRequest createDefaultPost = new CreatePostRequest("post", "content", false, categoryIdStr);
        Post defaultPost = createPostService.create(memberId, createDefaultPost);


        assertThat(defaultPost).isInstanceOf(DefaultPost.class);
        assertThat(defaultPost.getCategoryId()).isEqualTo(categoryId);
        assertThat(defaultPost.getPostContent().getContent()).isEqualTo("content");
        assertThat(defaultPost.getPostContent().getTitle()).isEqualTo("post");
        assertThat(defaultPost.getAuthor()).isEqualTo(author);

        CreatePostRequest createPremiumPost = new CreatePostRequest("post", "content", true, categoryIdStr);
        Post premiumPost = createPostService.create(memberId, createPremiumPost);
        assertThat(premiumPost).isInstanceOf(PremiumPost.class);

    }

}