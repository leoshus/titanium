/**
 * @author shangyd
 * @Date 2015年5月21日 下午8:49:59
 * Copyright (c) 2015
 **/
package com.sdw.soft.test.guava.basicutils;

import java.util.Set;

import org.junit.Test;

import com.google.common.base.Optional;

public class OptionalDemo {

	@Test
	public void test01(){
		
		Optional<Integer> optional = Optional.of(5);//1
		System.out.println(optional.isPresent());//查看1中的参数是否为null 为null返回false 不为空返回true
		System.out.println(optional.get()); //获取1中的内容  若1中参数为空 则抛出IllegalStateException异常
		Optional<String> fromNullable = Optional.fromNullable(null);//2 这里的参数 可为空
		System.out.println(fromNullable.isPresent());
		System.out.println(fromNullable.or("defaultValue"));//如果2中为空 则返回or中的默认值
		System.out.println(fromNullable.get());
		
		Set<String> asSet = fromNullable.asSet();
		System.out.println(asSet.size());//返回不可修改的set 该Set中包含Optional实例中包含的所有非空存在的T实例，且在该Set中，每个T实例都是单态，如果Optional中没有非空存在的T实例，返回的将是一个空的不可修改的Set。
	}
}
