package com.hxkj.constant;

@SuppressWarnings("all")
public enum DomainField {

    ID("domainId", "DOMAIN_ID"),
    NAME("domainName", "DOMAIN_NAME"),
    CODE("code", "CODE"),
    PARENT_CODE("parentCode", "PARENT_CODE"),
    CREATE_TIME("createTime", "CREATE_TIME"),
    UPDATE_TIME("updateTime", "UPDATE_TIME"),
    STATE("domainState", "DOMAIN_STATE"),
    RANK("rank", "RANK");

    public String attr;

    public String col;

    DomainField(String attr, String col) {
        this.attr = attr;
        this.col = col;
    }

}
