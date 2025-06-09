package com.portfolio.community.resource.domain;

public interface StorageStrategy {
    void save(byte[] data, String filename);

    void delete(String filename);
}