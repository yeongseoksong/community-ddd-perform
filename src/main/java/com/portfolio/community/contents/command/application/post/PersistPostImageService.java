package com.portfolio.community.contents.command.application.post;

import com.portfolio.community.contents.command.application.post.GetPostService;
import com.portfolio.community.contents.command.domain.post.Author;
import com.portfolio.community.contents.command.domain.post.Post;
import com.portfolio.community.contents.command.domain.post.PostId;
import com.portfolio.community.resource.application.PersistResourceService;
import com.portfolio.community.resource.domain.Resource;
import com.portfolio.community.resource.domain.ResourceFactory;
import com.portfolio.community.resource.domain.ResourceRepository;
import com.portfolio.community.resource.domain.StorageStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PersistPostImageService {

    private final GetPostService getPostService;
    private final PersistResourceService persistResourceService;

    public Resource validatePostAndPersistImage(Author author, PostId id,MultipartFile multipartFile){
        Post post = getPostService.getByIdFromAuthor(author, id);
        Resource resource = persistResourceService.persistMultipartFile(multipartFile);




    }



}
