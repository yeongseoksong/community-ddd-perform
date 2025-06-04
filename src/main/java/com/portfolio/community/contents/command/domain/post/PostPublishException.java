package com.portfolio.community.contents.command.domain.post;

import com.portfolio.community.common.CommonException;

public class PostPublishException extends CommonException {
    private static final String MESSAGE = "cannot publish post";

    public PostPublishException(){
        super(MESSAGE);
    }
}
