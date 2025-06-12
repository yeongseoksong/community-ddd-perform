package com.portfolio.community.resource.domain;


import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


@Slf4j
public class ResourceFactory {

    private static List<Class> clazzs = new CopyOnWriteArrayList<>();
    static  {
        clazzs.add(DefaultResource.class);
        clazzs.add(Image.class);

    }


    public static Resource generate(String fileName, String contentType, StorageStrategy storageStrategy){
       Resource resource=null;
        for(Class clazz: clazzs) {
            try {
                Constructor<Resource> constructor = clazz.getDeclaredConstructor(
                       String.class, StorageType.class, String.class
                );

                resource =constructor.newInstance(fileName, storageStrategy.getStorageType(), contentType);
                if(resource!=null)
                    break;


            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException | IllegalArgumentException e) {
                log.error("error occured : {}", e.getStackTrace());
            } catch (Exception e) {
                log.error("error occured : {}", e.getStackTrace());
            }
        }

        if(resource==null)
            throw new IllegalArgumentException("Can't create resource");

        return resource;
    }

}
