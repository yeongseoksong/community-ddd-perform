package com.portfolio.community.contents.command.application.post;


import com.portfolio.community.contents.command.application.category.GetCategoryService;
import com.portfolio.community.contents.command.domain.category.Category;
import com.portfolio.community.contents.command.domain.post.*;
import com.portfolio.community.contents.command.domain.postresource.PostResource;
import com.portfolio.community.contents.command.domain.postresource.PostResourceRepository;
import com.portfolio.community.contents.command.domain.postresource.ResourceTarget;
import com.portfolio.community.resource.application.PersistResourceService;
import com.portfolio.community.resource.domain.Resource;
import com.portfolio.community.resource.domain.ResourceId;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CreatePostService {

    private final PostRepository postRepository;
    private final GetCategoryService getCategoryService;
    private final PostResourceRepository postResourceRepository;
    private final PersistResourceService persistResourceService;

    public Post createPost(Author author, PostRequest postRequest, List<MultipartFile> uploadFiles) {

        Category category = getCategoryService.findById(postRequest.getCategoryId());
        PostContent postContent = new PostContent(postRequest.getTitle(), postRequest.getContent());
        Post post =  new Post(author,postContent,category.getId(), postRequest.getIsPremium());

        List<Resource> resources = persistResourceService.persistMultipartFiles(uploadFiles);

        Post save = postRepository.save(post);

        List<PostResource> postResourceStateList = new ArrayList<>();
        for(Resource resource :resources){
            ResourceId resourceId = resource.getId();
            PostResource postResource = new PostResource(save.getId(), new ResourceTarget(resourceId));
            postResourceStateList.add(postResource);
        }
        postResourceRepository.saveAll(postResourceStateList);

        return save;
    }

}
