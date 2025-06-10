package com.portfolio.community.resource.domain;

import com.portfolio.community.resource.infra.LocalStorage;
import org.apache.catalina.webresources.FileResource;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class ResourceFactoryTest {

    @Test
    public void 이미지_동적_생성_테스트() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Resource generate = ResourceFactory.generate("/test", "/test.txt", "image/png", new LocalStorage("/"));
        Assertions.assertThat(generate).isNotNull();
        Assertions.assertThat(generate).isInstanceOf(Image.class);

    }


    @Test
    public void 일반리소스_동적_생성_테스트() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Resource generate = ResourceFactory.generate("/test", "/test.txt", "__", new LocalStorage("/"));
        Assertions.assertThat(generate).isNotNull();
        Assertions.assertThat(generate).isInstanceOf(DefaultResource.class);

    }

}