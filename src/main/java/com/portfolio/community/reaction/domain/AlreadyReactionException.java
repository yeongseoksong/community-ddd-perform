package com.portfolio.community.reaction.domain;

import com.portfolio.community.common.CommonException;

public class AlreadyReactionException extends CommonException {
    private static final String MESSAGE = "Reaction already exists.";
    public AlreadyReactionException() {
        super(MESSAGE);
    }
}
