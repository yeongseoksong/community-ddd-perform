package com.portfolio.community.contents.command.application.postCategory;


import com.portfolio.community.contents.command.application.category.GetCategoryService;
import com.portfolio.community.contents.command.domain.post.Post;
import com.portfolio.community.contents.command.domain.postresource.PostResource;
import com.portfolio.community.contents.command.domain.postresource.PostResourceRepository;
import com.portfolio.community.contents.command.domain.postresource.ResourceTarget;
import com.portfolio.community.resource.domain.Resource;
import com.portfolio.community.resource.domain.ResourceId;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CreatePostCategoryService {

    private final PostResourceRepository postResourceRepository;


    public  PostResource mappingPostAndResources( Post post, Resource resource ) {
        ResourceId resourceId = resource.getId();
        PostResource postResource = new PostResource(post.getId(), new ResourceTarget(resourceId));
        return postResourceRepository.save(postResource);
    }

    public  List<PostResource> mappingPostAndResources( Post post,List<Resource> resources) {

        List<PostResource> postResourceStateList = new ArrayList<>();

        for(Resource resource :resources){
            ResourceId resourceId = resource.getId();
            PostResource postResource = new PostResource(post.getId(), new ResourceTarget(resourceId));
            postResourceStateList.add(postResource);
        }
        return postResourceRepository.saveAll(postResourceStateList);
    }


}
