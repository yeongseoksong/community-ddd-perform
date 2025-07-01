package com.portfolio.community.common;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class PaginationTest {

    private final Long totalCount =100L;

    private final Integer pageSize = 2;

    private final Integer navSize =5;

    @Test
    public void 첫번째_페이지는_이전이_없다(){
        Integer currentTotalPage= 0;
        Pagination pagination = new Pagination(totalCount, currentTotalPage, pageSize, navSize);
        assertThat(pagination.getHasPrev()).isFalse();

    }
    @Test
    public void 그외의_페이지는_이전과_다음이_있다(){
        Integer currentTotalPage= 1;
        Pagination pagination = new Pagination(totalCount, currentTotalPage, pageSize, navSize);
        assertThat(pagination.getHasPrev()).isTrue();
        assertThat(pagination.getHasNext()).isTrue();
    }

    @Test
    public void 마지막_페이지는_다음이_없다(){
        Integer currentTotalPage= (int) (totalCount/pageSize);
        Pagination pagination = new Pagination(totalCount, currentTotalPage, pageSize, navSize);
        assertThat(pagination.getHasNext()).isFalse();
    }



    @Test
    public void 페이지가_0일때_0_5 (){
        Integer currentTotalPage= 0;

        Pagination pagination = new Pagination(totalCount, currentTotalPage, pageSize, navSize);
        assertThat(pagination.getCurrentPage()).isEqualTo(currentTotalPage);
        assertThat(pagination.getStartNav()).isEqualTo(0);
        assertThat(pagination.getEndNav()).isEqualTo(4);
    }

    @Test
    public void 페이지가_4일때_0_5 (){
        Integer currentTotalPage= 4;
        Pagination pagination = new Pagination(totalCount, currentTotalPage, pageSize, navSize);
        assertThat(pagination.getCurrentPage()).isEqualTo(currentTotalPage);
        assertThat(pagination.getStartNav()).isEqualTo(0);
        assertThat(pagination.getEndNav()).isEqualTo(4);
    }

    @Test
    public void 페이지가_5일때_0_5 (){
        Integer currentTotalPage= 5;
        Pagination pagination = new Pagination(totalCount, currentTotalPage, pageSize, navSize);
        assertThat(pagination.getCurrentPage()).isEqualTo(currentTotalPage);
        assertThat(pagination.getStartNav()).isEqualTo(5);
        assertThat(pagination.getEndNav()).isEqualTo(9);
    }

    @Test
    public void 페이지가_50일때 (){
        Integer currentTotalPage= 50;
        Pagination pagination = new Pagination(totalCount, currentTotalPage, pageSize, navSize);
        assertThat(pagination.getCurrentPage()).isEqualTo(currentTotalPage);
        assertThat(pagination.getStartNav()).isEqualTo(50);
        assertThat(pagination.getEndNav()).isEqualTo(50);
    }

}