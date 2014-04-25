/**
 * @Date 2014年4月25日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.common.test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * 
 * @author syd
 */
@ActiveProfiles("mysql")
@ContextConfiguration(locations={"classpath:/context/spring-*.xml","classpath:/spring-mvc.xml","classpath:/service/spring-soap*.xml"})
public abstract class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	protected DataSource dataSource;

	@Override
	@Autowired
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
		this.dataSource = dataSource;
	}
	
}
