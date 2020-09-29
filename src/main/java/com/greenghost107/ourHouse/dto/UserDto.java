/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.dto;

public class UserDto {
	
	private String userName;
	
	public UserDto(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
