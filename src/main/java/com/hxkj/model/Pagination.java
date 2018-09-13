package com.hxkj.model;

import java.io.Serializable;

/**
 * @author dsd
 * @version 2018/6/18 23:07
 */
@SuppressWarnings("all")
public class Pagination implements Serializable {

    private static final long serialVersionUID = -5111149568542860181L;

    private Long currPage = 1L;

    private Long pageSize = 20L;

    private Long pageCount;

    private Long recordCount;

    public Long getCurrPage() {
        return currPage;
    }

    public Pagination setCurrPage(Long currPage) {
        this.currPage = currPage < 1L ? 1L : currPage;
        return this;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public Pagination setPageSize(Long pageSize) {
        this.pageSize = pageSize > 0L ? pageSize : 20L;
        return this;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public Pagination setPageCount(Long pageCount) {
        this.pageCount = pageCount;
        return this;
    }

    public Long getRecordCount() {
        return recordCount;
    }

    public Pagination setRecordCount(Long recordCount) {
        this.recordCount = recordCount;
        this.pageCount = recordCount % pageSize > 0 ? recordCount / pageSize + 1 : recordCount / pageSize;
        return this;
    }

}
