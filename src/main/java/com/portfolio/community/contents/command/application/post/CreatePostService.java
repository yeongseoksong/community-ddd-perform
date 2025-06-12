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


    public Post createPost(Author author, PostRequest postRequest, List<MultipartFile> attachments) {

        Category category = getCategoryService.getById(postRequest.getCategoryId());
        PostContent postContent = new PostContent(postRequest.getTitle(), postRequest.getContent());
        Post post =  new Post(author,postContent,category.getId(), postRequest.getIsPremium());
        Post save = postRepository.save(post);
        if(attachments==null || attachments.isEmpty()){
           return save;
        }

        List<Resource> resources = persistResourceService.persistMultipartFiles(attachments);
        createPostCategoryService.mappingPostAndResources(post,resources);

        return save;
    }

}
