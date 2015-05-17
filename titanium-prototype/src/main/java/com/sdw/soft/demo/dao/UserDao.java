/**
 * @author shangyd
 * @Date 2015年5月17日 下午7:05:16
 * Copyright (c) 2015
 **/
package com.sdw.soft.demo.dao;

import com.sdw.soft.core.mybatis.TitaniumRepository;
import com.sdw.soft.demo.vo.Demo;

@TitaniumRepository
public interface UserDao {

	public int addUser(Demo demo);
}
