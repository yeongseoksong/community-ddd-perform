package com.portfolio.community.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.print.Pageable;

@Data

public class Pagination {
    private Integer currentPage;
    private Integer totalPages;
    private Integer startNav;
    private Integer endNav;
    private Boolean hasPrev =true;
    private Boolean hasNext =true;


    public Pagination(Long totalCount, Integer currentPage ,Integer pageSize,Integer navSize){
        if(currentPage == null || currentPage==null || pageSize ==null || navSize==null  )
            throw new  IllegalArgumentException("Pagination currentPage, totalPages, pageSize, navSize  cannot be null");

        this.totalPages = (int) Math.ceil((double) totalCount / pageSize)-1;

        if(totalPages<currentPage)
            throw new PageOverFlowException();

        this.currentPage=currentPage;

        this.startNav=(this.currentPage / navSize) * navSize;
        this.endNav= Math.min(startNav+navSize-1, totalPages);

        if(currentPage==0) hasPrev=false;
        if(totalPages==endNav) hasNext=false;

    }
}
