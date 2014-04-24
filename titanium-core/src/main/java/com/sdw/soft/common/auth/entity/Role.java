package com.sdw.soft.common.auth.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.sdw.soft.core.annotation.MetaData;
import com.sdw.soft.core.entity.BaseEntity;

/**
 * @author syd
 * @Date 2013年12月4日
 * @version 1.0.0
 * Copyright (c) 2013
 * 
 * 角色对象定义
 */
@Entity
@Table(name = "T_AUTH_ROLE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@MetaData(title = "角色")
public class Role extends BaseEntity<String> {

	private static final long serialVersionUID = 6576334675217310936L;
	public static final String ROLE_ADMIN_CODE = "ROLE_ADMIN";
    public static final String ROLE_ANONYMOUSLY_CODE = "ROLE_ANONYMOUSLY";
    public static final String ROLE_PROTECTED_CODE = "ROLE_PROTECTED";
    /** 通过第三方认证过来的用户，默认赋予TBD角色，以便进行后续用户绑定等操作 */
    public static final String ROLE_TBD_USER_CODE = "ROLE_TBD_USER";
    
    @MetaData(title = "代码", description = "必须以ROLE_打头")
    private String code = "ROLE_";

    @MetaData(title = "名称")
    private String title;

    @MetaData(title = "描述")
    private String description;

    @MetaData(title = "禁用标识", description = "禁用角色不参与权限控制逻辑")
    private Boolean disabled = Boolean.FALSE;

    @MetaData(title = "锁定标识", description = "对于不允许随意调整配置的角色，可以设定为锁定则只能查看无法编辑")
    private Boolean locked = Boolean.FALSE;

    @MetaData(title = "角色权限关联")
    private List<RoleR2Privilege> roleR2Privileges = Lists.newArrayList();

    private String id;

    @Id
    @Column(length = 40)
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    @Column(nullable = false, length = 256)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(nullable = true, length = 2000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Size(min = 6)
    @Pattern(regexp = "^ROLE_.*", message = "必须以[ROLE_]开头")
    @Column(nullable = false, length = 64, unique = true)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    @Override
    @Transient
    public String getDisplayLabel() {
        return title;
    }

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    public List<RoleR2Privilege> getRoleR2Privileges() {
        return roleR2Privileges;
    }

    public void setRoleR2Privileges(List<RoleR2Privilege> roleR2Privileges) {
        this.roleR2Privileges = roleR2Privileges;
    }

}
