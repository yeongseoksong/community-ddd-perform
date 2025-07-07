package com.portfolio.community.reaction.domain;

import com.portfolio.community.common.response.MotherException;

public class ReactionNotFoundException extends MotherException {
    private static final String MESSAGE = "Reaction not found";

    public ReactionNotFoundException() {
        super(MESSAGE);
    }
}
