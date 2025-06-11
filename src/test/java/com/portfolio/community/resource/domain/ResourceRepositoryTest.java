package com.portfolio.community.resource.domain;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.stream.Collectors;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ResourceRepositoryTest {

    @Autowired private ResourceRepository resourceRepository;
    @Test
    public void bulkUpdateResourceState() {
        Resource image1 = new Image("/test","test.txt",StorageType.LOCAL,"image/png");
        Resource image2 = new Image("/test","test.txt",StorageType.LOCAL,"image/png");
        Resource image3 = new Image("/test","test.txt",StorageType.LOCAL,"image/png");

//        Resource save = resourceRepository.save(resource);
        List<Resource> images = List.of(image1,image2,image3);
        List<Resource> resources = resourceRepository.saveAll(images);
        List<ResourceId> resourceIds = resources.stream().map(r -> r.getId()).collect(Collectors.toList());
        int i = resourceRepository.bulkUpdateResourceState(ResourceState.DELETE,resourceIds);

        Assertions.assertThat(i).isEqualTo(3);
        resourceRepository.flush();

        List<Resource> finds = resourceRepository.findAllById(resourceIds);
        for(var find:finds){
            Assertions.assertThat(find.getState()).isEqualTo(ResourceState.DELETE);
        }

    }
}