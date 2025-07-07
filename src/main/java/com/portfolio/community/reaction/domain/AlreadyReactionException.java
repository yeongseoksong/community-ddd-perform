package com.portfolio.community.reaction.domain;

import com.portfolio.community.common.response.MotherException;

public class AlreadyReactionException extends MotherException {
    private static final String MESSAGE = "이미 해당 게시글에 추천, 비추천을 누르셨습니다.";
    public AlreadyReactionException() {
        super(MESSAGE);
    }
}
