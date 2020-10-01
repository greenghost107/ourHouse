/*
 * Created by greenghost107 on Oct/2020
 */
package com.greenghost107.ourHouse.service.security;

public interface SecurityService {
	String findLoggedInUsername();
	
	void autoLogin(String username, String password);
}
