package com.portfolio.community.contents.command.domain.post;

import com.portfolio.community.common.response.MotherException;
import org.springframework.http.HttpStatus;

public class PostPublishException extends MotherException {
    private static final String MESSAGE = "cannot publish post";

    public PostPublishException(){
        super(MESSAGE);
    }
}
