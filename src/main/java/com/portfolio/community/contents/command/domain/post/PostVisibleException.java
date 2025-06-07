package com.portfolio.community.contents.command.domain.post;

import com.portfolio.community.common.response.MotherException;
import org.springframework.http.HttpStatus;

public class PostVisibleException extends MotherException {
    private static final String MESSAGE = "post isn't visible";

    public PostVisibleException() {
        super(MESSAGE);
    }
}
