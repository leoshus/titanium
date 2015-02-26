package com.sdw.soft.test;

import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class ActiveMQDemo {

	public static void main(String[] args){
		ResourceLoader loader = new DefaultResourceLoader();
		Resource resource = loader.getResource("classpath:com/sdw/soft/test/spring-test.xml");
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		try {
			BeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
			int count = reader.loadBeanDefinitions(resource);
			System.out.println("loaded bean count="+count);
			SpringTest test = (SpringTest)beanFactory.getBean("springtest");
			test.print();
			System.out.println("-----------------------------");
			test = (SpringTest)beanFactory.getBean("springtest");
			test.print();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
