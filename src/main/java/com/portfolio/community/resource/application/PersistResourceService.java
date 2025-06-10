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

            Resource resource;

            if (isImageContentType(contentType)) {
                resource = new Image(path, multipartFile.getOriginalFilename(),storageStrategy.getStorageType() );
            } else {
                resource = new DefaultResource(path, multipartFile.getOriginalFilename(), storageStrategy.getStorageType());
            }
            return resourceRepository.save(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isImageContentType(String contentType) {
        return contentType != null &&
                (contentType.equals("image/png")
                        || contentType.equals("image/jpeg")
                        || contentType.equals("image/jpg"));
    }

}
