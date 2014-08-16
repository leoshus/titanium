/**
 * @Date 2014年8月16日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.core.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.sdw.soft.core.annotation.MetaData;

/**
 * 
 * @author syd
 */
public abstract class BaseUUIDEntity extends BaseEntity<String>{

	private static final long serialVersionUID = -2626315179103164502L;

	@MetaData(title="UUID主键")
	private String id;
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	@Id
	@Column(length = 40)
	@GeneratedValue(generator="hibernate-uuid")
	@GenericGenerator(name="hibernate-uuid",strategy="uuid")
	public void setId(String id) {
		this.id = id;
	}

}
