package com.portfolio.community.contents.command.domain.category;

import com.portfolio.community.common.response.MotherException;

public class CategoryNotFoundException extends MotherException {
    private static String MESSAGE = "category not found";

    public CategoryNotFoundException() {
        super(MESSAGE);
    }
}
