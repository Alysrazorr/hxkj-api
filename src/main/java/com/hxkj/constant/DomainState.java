package com.hxkj.constant;

@SuppressWarnings("all")
public enum DomainState {

    PRE_BUILD("预创建"),
    NOT_ENABLED("未启用"), //
    ENABLED("已启用"), //
    DISABLED("已停用"), //
    OBSOLETE("已废弃"), //
    DELETED("已删除");

    public String desc;

    DomainState(String desc) {
        this.desc = desc;
    }

}
