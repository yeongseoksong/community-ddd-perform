package com.portfolio.community.resource.application;

import com.portfolio.community.resource.domain.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DeleteResourceSerivce {

    private final StorageStrategy storageStrategy;
    private final ResourceRepository resourceRepository;

    public DeleteResourceSerivce(@Qualifier("localStorage") StorageStrategy storageStrategy, ResourceRepository resourceRepository) {
        this.storageStrategy = storageStrategy;
        this.resourceRepository= resourceRepository;
    }

    @Transactional
    public void persistMultipartFile(ResourceId id){
            Resource resource = resourceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Resource not found"));
            String fileName = resource.getFileName();
            storageStrategy.delete(fileName);
            resource.delete();
    }


}
