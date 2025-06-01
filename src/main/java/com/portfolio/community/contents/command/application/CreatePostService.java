package com.portfolio.community.contents.command.application;


import com.portfolio.community.contents.command.domain.category.Category;
import com.portfolio.community.contents.command.domain.category.CategoryId;
import com.portfolio.community.contents.command.domain.category.CategoryRepository;
import com.portfolio.community.contents.command.domain.post.*;
import com.portfolio.community.member.command.domain.MemberId;
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

    public Post create(MemberId memberId, CreatePostRequest createPostRequest) {
        Author author =authorService.createAuthor(memberId);
        Category category = categoryRepository.findById(new CategoryId(createPostRequest.getCategoryId())).orElseThrow(EntityNotFoundException::new);
        PostContent postContent = new PostContent(createPostRequest.getTitle(), createPostRequest.getContent());

        Post post;
        if(createPostRequest.getIsPremium())
            post = new PremiumPost(author,postContent,category.getId());
        else
            post=new DefaultPost(author,postContent, category.getId());

        return postRepository.save(post);
    }

}
