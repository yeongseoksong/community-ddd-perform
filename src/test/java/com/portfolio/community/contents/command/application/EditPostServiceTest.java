package com.portfolio.community.contents.command.application;

import com.portfolio.community.contents.command.application.category.GetCategoryService;
import com.portfolio.community.contents.command.application.post.CreatePostService;
import com.portfolio.community.contents.command.application.post.EditPostService;
import com.portfolio.community.contents.command.application.post.PostRequest;
import com.portfolio.community.contents.command.domain.category.Category;
import com.portfolio.community.contents.command.domain.category.CategoryRepository;
import com.portfolio.community.contents.command.domain.post.*;
import com.portfolio.community.contents.command.domain.postresource.PostResource;
import com.portfolio.community.contents.command.domain.postresource.PostResourceRepository;
import com.portfolio.community.member.command.domain.MemberId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Optional;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest(properties = {"storage.path=/tmp/resources"})
class EditPostServiceTest {

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GetCategoryService getCategoryService;

    @Autowired
    private EditPostService editPostService;

    @Autowired
    private CreatePostService createPostService;

    @Autowired
    PostResourceRepository postResourceRepository;

    private final MockMultipartFile file = new MockMultipartFile(
            "file",                            // 파라미터 이름
            "test.txt",                        // 원본 파일명
            "text/plain",                      // 콘텐츠 타입
            "This is a test file".getBytes()   // 파일 내용
    );
    @Test
    public void 리소스와_함께_게시글을_편집할_수_있다() {
        MemberId memberId = new MemberId();;
        Author author = new Author(memberId,"Test");
        when(authorService.createAuthor(memberId)).thenReturn(author);

        Category free = new Category( "free");

        doNothing().when(getCategoryService).assertById(free.getId());

        Post initialPost = createPostService.createInitialPost(author, free.getId());

        PostRequest createDefaultPost = new PostRequest("post", "content", false, free.getId());
        Post defaultPost = editPostService.editPost(initialPost.getId() ,author,createDefaultPost,List.of(file));


        assertThat(defaultPost).isInstanceOf(Post.class);
        assertThat(defaultPost.getCategoryId()).isEqualTo(free.getId());
        assertThat(defaultPost.getPostContent().getContent()).isEqualTo("content");
        assertThat(defaultPost.getPostContent().getTitle()).isEqualTo("post");
        assertThat(defaultPost.getAuthor()).isEqualTo(author);

        List<PostResource> byPostId = postResourceRepository.findByPostId(initialPost.getId());
        assertThat(byPostId).hasSize(1);
    }

    @Test
    public void 리소스와_없이도_게시글을_편집할_수_있다() {
        MemberId memberId = new MemberId();;
        Author author = new Author(memberId,"Test");
        when(authorService.createAuthor(memberId)).thenReturn(author);

        Category free = new Category( "free");

        doNothing().when(getCategoryService).assertById(free.getId());

        Post initialPost = createPostService.createInitialPost(author, free.getId());

        PostRequest createDefaultPost = new PostRequest("post", "content", false, free.getId());
        Post defaultPost = editPostService.editPost(initialPost.getId() ,author,createDefaultPost,null);


        assertThat(defaultPost).isInstanceOf(Post.class);
        assertThat(defaultPost.getCategoryId()).isEqualTo(free.getId());
        assertThat(defaultPost.getPostContent().getContent()).isEqualTo("content");
        assertThat(defaultPost.getPostContent().getTitle()).isEqualTo("post");
        assertThat(defaultPost.getAuthor()).isEqualTo(author);

        List<PostResource> byPostId = postResourceRepository.findByPostId(initialPost.getId());
        assertThat(byPostId).hasSize(0);
    }

}