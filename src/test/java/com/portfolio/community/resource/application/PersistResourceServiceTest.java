package com.portfolio.community.resource.application;

import com.portfolio.community.resource.domain.Resource;
import com.portfolio.community.resource.domain.ResourceRepository;
import com.portfolio.community.resource.domain.ResourceState;
import com.portfolio.community.resource.infra.LocalStorage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;


@SpringBootTest(properties = {
        "storage.path=/tmp/resources"
})
class PersistResourceServiceTest {
    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private  LocalStorage localStorage;

   @Autowired
   PersistResourceService persistResourceService = new PersistResourceService(localStorage,resourceRepository);

    private final MockMultipartFile file = new MockMultipartFile(
            "file",                            // 파라미터 이름
            "test.txt",                        // 원본 파일명
            "text/plain",                      // 콘텐츠 타입
            "This is a test file".getBytes()   // 파일 내용
    );
    @Test
    public void 리소스_저장_서비스는_정상동작한다(){
        Resource save = persistResourceService.persistMultipartFile(file);
        Assertions.assertThat(save.getFileName()).isEqualTo("test.txt");
        Assertions.assertThat(save.getState()).isEqualTo(ResourceState.ACTIVE);
        Assertions.assertThat(save.getPath()).isEqualTo(localStorage.getBasePath()+"\\"+save.calcFileName());
        Assertions.assertThat(save.getStorageType()).isEqualTo(localStorage.getStorageType());
    }
}