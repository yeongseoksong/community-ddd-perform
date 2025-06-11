package com.portfolio.community.resource.application;

import com.portfolio.community.resource.domain.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class DeleteResourceSerivce {

    private final StorageStrategy storageStrategy;
    private final ResourceRepository resourceRepository;

    public DeleteResourceSerivce(@Qualifier("localStorage") StorageStrategy storageStrategy, ResourceRepository resourceRepository) {
        this.storageStrategy = storageStrategy;
        this.resourceRepository= resourceRepository;
    }


    public void deleteResource(ResourceId id){
            Resource resource = resourceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Resource not found"));
            String fileName = resource.getFileName();
            storageStrategy.delete(fileName);
            resource.delete();
    }

    public void deleteResources(List<ResourceId> resourceIdList){
        int n = resourceRepository.bulkUpdateResourceState(ResourceState.DELETE, resourceIdList);
        log.info("{} Resource deleted ",n);

    }


}
