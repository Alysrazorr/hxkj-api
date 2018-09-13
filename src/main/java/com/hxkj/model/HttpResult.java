package com.hxkj.model;

import java.io.Serializable;

/**
 * @author dsd
 * @version 2018/6/18 23:07
 */
@SuppressWarnings("all")
public class HttpResult implements Serializable {

    public static final int SUCCESS = 233;

    public static final int FAILED = 440;

    public static final int LOGIN_TIMEOUT = 441;

    public static final int NOT_LOGGED_IN = 442;

    private Object data;

    private Pagination pagination;

    private String message;

    private Integer statusCode;

    public Object getData() {
        return this.data;
    }

    public HttpResult setData(Object data) {
        this.data = data;
        return this;
    }

    public Pagination getPagination() {
        return this.pagination;
    }

    public HttpResult setPagination(Pagination pagination) {
        this.pagination = pagination;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public HttpResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public HttpResult setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
        return this;
    }
}
