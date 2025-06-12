package com.portfolio.community.resource.domain;

import java.nio.file.Path;

public interface StorageStrategy {
    void save(byte[] data, String fileName);

    void delete(String filename);

    Path calcPath(String fileName);


    StorageType getStorageType();
}