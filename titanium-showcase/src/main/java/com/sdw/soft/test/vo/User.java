/**
 * @Date 2014年8月12日
 * @version 1.0.0
 * Copyright (c) 2014
 */
package com.sdw.soft.test.vo;
/**
 * 
 * @author syd
 */
public class User {

	private String username;
	private String password;
	private int age;
	public User() {
		super();
	}
	public User(String username, String password, int age) {
		super();
		this.username = username;
		this.password = password;
		this.age = age;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}
