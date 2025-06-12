package com.portfolio.community.contents.command.application.post;

import com.portfolio.community.contents.command.application.postCategory.CreatePostCategoryService;
import com.portfolio.community.contents.command.domain.post.Author;
import com.portfolio.community.contents.command.domain.post.Post;
import com.portfolio.community.contents.command.domain.post.PostId;
import com.portfolio.community.contents.command.domain.postresource.PostResource;
import com.portfolio.community.resource.application.PersistResourceService;
import com.portfolio.community.resource.domain.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class PersistPostResourceService {

    private final GetPostService getPostService;
    private final PersistResourceService persistResourceService;
    private final CreatePostCategoryService createPostCategoryService;
    

    public Resource validatePostAndPersistImage(Author author, PostId id,MultipartFile multipartFile){
        Post post = getPostService.getByIdFromAuthor(author, id);
        Resource resource = persistResourceService.persistMultipartFile(multipartFile);


        PostResource postResource = createPostCategoryService.mappingPostAndResources(post, resource);
        return resource;
    }

}
