/**
 * @Date 2014年4月25日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.test;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.hibernate.ejb.EntityManagerFactoryImpl;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;

import com.sdw.soft.common.test.BaseTest;

/**
 * 
 * @author syd
 */
public class JpaTestMapping extends BaseTest {

	private static final Logger logger = LoggerFactory.getLogger(JpaTestMapping.class);
	
	@SuppressWarnings("rawtypes")
	@Test
	public void test01(){
		Metamodel model = entityManager.getEntityManagerFactory().getMetamodel();
		assertTrue("No entity mapping found", model.getEntities().size() > 0);
		for (EntityType entityType : model.getEntities()) {
			String entityName = entityType.getName();
			if(!"DefaultRevisionEntity".equals(entityName)){
				entityManager.createQuery("select a from "+ entityName +" a").getResultList();
				logger.info("ok:" + entityName);
			}
		}
		EntityManagerFactoryInfo entityManagerFactoryInfo = (EntityManagerFactoryInfo)applicationContext.getBean("entityManagerFactory");
		EntityManagerFactory entityManagerFactory = entityManagerFactoryInfo.getNativeEntityManagerFactory();
		EntityManagerFactoryImpl emfImpl = (EntityManagerFactoryImpl)entityManagerFactory;
		logger.debug("Hibernate Cache Statistics:{}",emfImpl.getSessionFactory().getStatistics());
		
		
	}
}
