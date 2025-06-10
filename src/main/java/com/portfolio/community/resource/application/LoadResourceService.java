package com.portfolio.community.resource.application;


import com.portfolio.community.resource.domain.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class LoadResourceService {

    private final StorageStrategy storageStrategy;
    private final ResourceRepository resourceRepository;

    public LoadResourceService(@Qualifier("localStorage") StorageStrategy storageStrategy, ResourceRepository resourceRepository) {
        this.storageStrategy = storageStrategy;
        this.resourceRepository= resourceRepository;
    }

    public Resource loadResource(ResourceId id){

            Resource resource = resourceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Resource not found"));

            return resource;

    }
}
