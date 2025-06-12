package com.portfolio.community.resource.domain;

import com.portfolio.community.resource.infra.LocalStorage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceTest {


    @Test
    public void 저장될_파일이름은_확장자와_아이디_값을_더한다(){
        Resource resource = ResourceFactory.generate( "test.txt", "application/json", new LocalStorage("/tmp/resources"));
        Assertions.assertThat(resource.calcFileName()).isEqualTo(resource.getId().getValue()+"."+"txt");
    }
}