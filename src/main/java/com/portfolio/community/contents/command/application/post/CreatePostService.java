package com.portfolio.community.contents.command.application.post;


import com.portfolio.community.contents.command.application.category.GetCategoryService;
import com.portfolio.community.contents.command.application.postCategory.CreatePostCategoryService;
import com.portfolio.community.contents.command.domain.category.Category;
import com.portfolio.community.contents.command.domain.category.CategoryId;
import com.portfolio.community.contents.command.domain.post.*;
import com.portfolio.community.resource.application.PersistResourceService;
import com.portfolio.community.resource.domain.Resource;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CreatePostService {

    private final PostRepository postRepository;
    private final GetCategoryService getCategoryService;
    private final PersistResourceService persistResourceService;
    private final CreatePostCategoryService createPostCategoryService;

    public Post createInitialPost(Author author, CategoryId categoryId) {

        Category category = getCategoryService.getById(categoryId);
        Post post =  new Post(author,categoryId);

        return postRepository.save(post);
    }



}
