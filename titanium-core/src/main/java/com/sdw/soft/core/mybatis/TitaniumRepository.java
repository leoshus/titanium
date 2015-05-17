package com.sdw.soft.core.mybatis;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/*
 *@author shangyd
 *@date 2015年2月3日
 *@description 标识MyBatis的Dao 方便{@link org.mybatis.spring.mapper.MapperScannerConfigurer}的扫描。
 * Copyright (c) 2015
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface TitaniumRepository {
	String value() default "";
}
