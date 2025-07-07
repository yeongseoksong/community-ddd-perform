package com.portfolio.community.common;

import com.portfolio.community.common.response.MotherException;

public class PageOverFlowException extends MotherException {

    private static final String MESSAGE ="요청하신 페이지가 존재하지 않습니다.";
    public PageOverFlowException() {
        super(MESSAGE);
    }
}
