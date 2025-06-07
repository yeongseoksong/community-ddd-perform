package com.portfolio.community.reaction.domain;

import com.portfolio.community.common.response.MotherException;

public class AlreadyReactionException extends MotherException {
    private static final String MESSAGE = "Reaction already exists.";
    public AlreadyReactionException() {
        super(MESSAGE);
    }
}
