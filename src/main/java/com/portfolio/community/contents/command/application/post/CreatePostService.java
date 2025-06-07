package com.portfolio.community.contents.command.application.post;


import com.portfolio.community.contents.command.application.category.GetCategoryService;
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
    private final GetCategoryService getCategoryService;
    private final AuthorService authorService;

    public Post createPost(Author author,PostRequest postRequest) {

        Category category = getCategoryService.findById(postRequest.getCategoryId());
        PostContent postContent = new PostContent(postRequest.getTitle(), postRequest.getContent());
        Post post =  new Post(author,postContent,category.getId(), postRequest.getIsPremium());

        return postRepository.save(post);
    }

}
