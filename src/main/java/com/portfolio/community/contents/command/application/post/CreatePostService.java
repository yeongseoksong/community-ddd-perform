package com.portfolio.community.contents.command.application.post;


import com.portfolio.community.contents.command.domain.category.Category;
import com.portfolio.community.contents.command.domain.category.CategoryRepository;
import com.portfolio.community.contents.command.domain.post.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CreatePostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorService authorService;

    public Post createPost(CreatePostRequest createPostRequest) {
        Author author=createPostRequest.getAuthor();
        Category category = categoryRepository.findById(createPostRequest.getCategoryId()).orElseThrow(EntityNotFoundException::new);
        PostContent postContent = new PostContent(createPostRequest.getTitle(), createPostRequest.getContent());
        Post post =  new Post(author,postContent,category.getId(),createPostRequest.getIsPremium());

        return postRepository.save(post);
    }

}
