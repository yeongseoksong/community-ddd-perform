package com.portfolio.community.common;

import lombok.Data;

@Data
public class Pagination {
    private int currentPage;
    private int totalPages;
    private int startNav;
    private int endNav;
    private boolean hasPrev;
    private boolean hasNext;
}
