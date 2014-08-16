/**
 * @Date 2014年8月16日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.sys.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdw.soft.core.annotation.MetaData;
import com.sdw.soft.core.entity.BaseUUIDEntity;

/**
 * 
 * @author syd
 */
public class Menu extends BaseUUIDEntity{

	private static final long serialVersionUID = 1436992987548539991L;
	
	@MetaData(title="菜单代码")
	private String code;
	@MetaData(title="菜单名称")
	private String title;
	
	@MetaData(title="描述")
	private String description;
	
	@MetaData(title="禁用标识")
	private Boolean disabled = Boolean.FALSE;
	
	@MetaData(title="菜单URL")
	private String url;
	
	@MetaData(title="图标")
	private String icon;
	
	@MetaData(title="菜单类型")
	private MenuTypeEnum type = MenuTypeEnum.RELC;
	
	@MetaData(title="菜单排序")
	private Integer orderRank = 100;
	
	@MetaData(title="菜单展开标识")
	private Boolean initOpen = Boolean.FALSE;
	
	@MetaData(title="父节点")
	private Menu parent;
	
	@MetaData(title="子节点")
	private List<Menu> children;
	
	public static enum MenuTypeEnum{
		@MetaData(title = "相对上下文")
        RELC,

        @MetaData(title = "相对域名")
        RELD,

        @MetaData(title = "绝对路径")
        ABS;
	}
	@Override
	public String getDisplayLabel() {
		return null;
	}
	@Column(nullable = false, unique = true, length = 64)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Column(length = 128, nullable = false)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(length = 1000)
	@JsonIgnore
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Boolean getDisabled() {
		return disabled;
	}
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}
	@Column(length = 256)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(length = 128)
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	@Enumerated(EnumType.STRING)
    @Column(length = 32, nullable = false)
	public MenuTypeEnum getType() {
		return type;
	}
	public void setType(MenuTypeEnum type) {
		this.type = type;
	}
	public Integer getOrderRank() {
		return orderRank;
	}
	public void setOrderRank(Integer orderRank) {
		this.orderRank = orderRank;
	}
	public Boolean getInitOpen() {
		return initOpen;
	}
	public void setInitOpen(Boolean initOpen) {
		this.initOpen = initOpen;
	}
	@ManyToOne(cascade=CascadeType.DETACH)
	@JoinColumn(name="PARENT_ID")
	@JsonIgnore
	public Menu getParent() {
		return parent;
	}
	public void setParent(Menu parent) {
		this.parent = parent;
	}
	@OneToMany(mappedBy = "parent")
	@OrderBy("orderRank desc")
	@JsonIgnore
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	

}
