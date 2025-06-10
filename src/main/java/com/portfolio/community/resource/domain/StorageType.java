package com.portfolio.community.resource.domain;


import lombok.Getter;
@Getter
public enum StorageType {
    LOCAL("http://localhost:8080/api/resources"),REMOTE("s3...");

    private final String baseUrl;

    StorageType(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
