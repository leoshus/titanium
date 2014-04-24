/**
 * @Date 2014年4月24日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sdw.soft.core.utils.json.DateTimeJsonSerializer;

/**
 * 
 * @author syd
 */
@MappedSuperclass
@JsonInclude(Include.NON_EMPTY)
public abstract class BaseEntity<ID extends Serializable> extends AncestorEntity<ID> {
	
	private static final long serialVersionUID = -6350528902856416603L;

	/** 乐观锁版本,初始设置为0 */
    private int version = 0;

    /** 数据访问控制代码 */
    private String aclCode;

    /** 数据访问控制类型 */
    private Integer aclType;

    private String createdBy;

    private Date createdDate;

    private String lastModifiedBy;

    private Date lastModifiedDate;

    public abstract void setId(final ID id);

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.data.domain.Auditable#getCreatedBy()
     */
    @JsonIgnore
    @Column(updatable = false)
    public String getCreatedBy() {
        return createdBy;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.data.domain.Auditable#setCreatedBy(java.lang.Object)
     */
    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = DateTimeJsonSerializer.class)
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.data.domain.Auditable#getLastModifiedBy()
     */
    @JsonIgnore
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(final String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @JsonIgnore
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(final Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Column(length = 20, nullable = true)
    public String getAclCode() {
        return aclCode;
    }

    public void setAclCode(String aclCode) {
        this.aclCode = aclCode;
    }

    public Integer getAclType() {
        return aclType;
    }

    public void setAclType(Integer aclType) {
        this.aclType = aclType;
    }

    @Version
    @Column(nullable = true)
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void resetCommonProperties() {
        setId(null);
        version = 0;
        lastModifiedBy = null;
        lastModifiedDate = null;
        createdBy = null;
        createdDate = null;
        aclCode = null;
        aclType = null;
    }
}
