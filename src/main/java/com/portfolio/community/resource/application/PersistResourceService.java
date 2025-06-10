package com.portfolio.community.resource.application;

import com.portfolio.community.resource.domain.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PersistResourceService {

    private final StorageStrategy storageStrategy;
    private final ResourceRepository resourceRepository;

    public PersistResourceService(@Qualifier("localStorage") StorageStrategy storageStrategy, ResourceRepository resourceRepository) {
        this.storageStrategy = storageStrategy;
        this.resourceRepository= resourceRepository;
    }
    @Transactional
    public Resource persistMultipartFile(MultipartFile multipartFile){
        try {
            String contentType = multipartFile.getContentType();
            storageStrategy.save(multipartFile.getBytes(), multipartFile.getOriginalFilename());
            String path = storageStrategy.calcPath(multipartFile.getOriginalFilename());
            Resource resource= ResourceFactory.generate(path, multipartFile.getOriginalFilename(),contentType,storageStrategy);
            return resourceRepository.save(resource);

        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }

}
