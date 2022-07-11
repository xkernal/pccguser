package com.pccg.pccguser.interfaces.api;

import javax.validation.constraints.Max;

public class Page {

    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_PAGE_SIZE = 2;
    private Integer currentPage;
    @Max(value = 100)
    private Integer pageSize;

    public int getCurrentPage() {
        if(currentPage==null) {
            return DEFAULT_PAGE;
        }
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        if(pageSize==null){
            return DEFAULT_PAGE_SIZE;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
