package com.sdw.soft.core.webservice.test.soap;

import javax.jws.WebService;

@WebService(name="IUserInfoService")
public interface IUserInfoService {
	public String fetchUserInfo(String id) ;
}
