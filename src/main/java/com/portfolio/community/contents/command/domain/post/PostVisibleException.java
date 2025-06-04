package com.portfolio.community.contents.command.domain.post;

import com.portfolio.community.common.CommonException;

public class PostVisibleException extends CommonException {
    private static final String MESSAGE = "post isn't visible";

    public PostVisibleException() {
        super(MESSAGE);
    }
}
