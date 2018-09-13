package com.hxkj.domain;

import com.hxkj.constant.DomainState;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.SerializationUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("all")
@MappedSuperclass
public class BaseDomain implements Serializable {

    @Id
    @Column(name = "ID", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long domainId;

    @Column(name = "DOMAIN_NAME", length = 1000)
    protected String domainName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "CREATE_TIME", updatable = false)
    protected Date createTime = new Date();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "UPDATE_TIME")
    protected Date updateTime = new Date();

    @Enumerated(EnumType.STRING)
    @Column(name = "DOMAIN_STATE", length = 10)
    protected DomainState domainState = DomainState.ENABLED;

    @Column(name = "RANK")
    protected Integer rank = 100;

    public Object deepClone() {
        return SerializationUtils.clone(this);
    }

    @Override
    public int hashCode() {
        return domainId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return hashCode() == obj.hashCode();
    }

    public Long getDomainId() {
        return domainId;
    }

    public void setDomainId(Long domainId) {
        this.domainId = domainId;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public DomainState getDomainState() {
        return domainState;
    }

    public void setDomainState(DomainState domainState) {
        this.domainState = domainState;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

}
