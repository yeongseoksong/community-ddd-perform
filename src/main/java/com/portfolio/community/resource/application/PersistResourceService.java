package com.portfolio.community.resource.application;

import com.portfolio.community.resource.domain.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class PersistResourceService {

    private final StorageStrategy storageStrategy;
    private final ResourceRepository resourceRepository;

    public PersistResourceService(@Qualifier("localStorage") StorageStrategy storageStrategy, ResourceRepository resourceRepository) {
        this.storageStrategy = storageStrategy;
        this.resourceRepository= resourceRepository;
    }
    public Resource persistMultipartFile(MultipartFile multipartFile){
        try {
            String contentType = multipartFile.getContentType();
            Resource resource= ResourceFactory.generate(multipartFile.getOriginalFilename(),contentType,storageStrategy);
            Resource save = resourceRepository.save(resource);

            storageStrategy.save(multipartFile.getBytes(), resource.calcFileName());
            save.setPath(storageStrategy.calcPath(resource.calcFileName()).toString());

            save.setStateActive();
            return save;

        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }

    public List<Resource> persistMultipartFiles(List<MultipartFile> multipartFiles){
        List<Resource> resources =new ArrayList<>();
        for(MultipartFile multipartFile:multipartFiles)
            resources.add(persistMultipartFile(multipartFile));

        return resources;
    }

}
