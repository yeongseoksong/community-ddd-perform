package com.portfolio.community.resource.domain;



public class NoImageType extends RuntimeException {

    private static final String MESSAGE ="Unsupported image type";

    public NoImageType() {
        this(MESSAGE);
    }

    public NoImageType(String message) {
        super(message);
    }
}
