package com.portfolio.community.contents.command.application.post;

import com.portfolio.community.contents.command.application.category.GetCategoryService;
import com.portfolio.community.contents.command.application.postCategory.CreatePostCategoryService;
import com.portfolio.community.contents.command.domain.post.Author;
import com.portfolio.community.contents.command.domain.post.Post;
import com.portfolio.community.contents.command.domain.post.PostContent;
import com.portfolio.community.contents.command.domain.post.PostId;
import com.portfolio.community.resource.application.PersistResourceService;
import com.portfolio.community.resource.domain.Resource;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class EditPostService {
    private final GetPostService getPostService;
    private final GetCategoryService getCategoryService;
    private final PersistResourceService persistResourceService;
    private final CreatePostCategoryService createPostCategoryService;


    public Post editPost(PostId postId, Author author, PostRequest postRequest, List<MultipartFile> attachments) {
        getCategoryService.assertById(postRequest.getCategoryId());
        Post post =getPostService.getByIdFromAuthor(author,postId);
        PostContent postContent = new PostContent(postRequest.getTitle(), postRequest.getContent());
        post.editPostContent(postContent, postRequest.getCategoryId(), postRequest.getIsPremium());
        if(attachments==null || attachments.isEmpty()){
            return post;
        }

        List<Resource> resources = persistResourceService.persistMultipartFiles(attachments);
        createPostCategoryService.mappingPostAndResources(post,resources);

        return post;
    }
}
