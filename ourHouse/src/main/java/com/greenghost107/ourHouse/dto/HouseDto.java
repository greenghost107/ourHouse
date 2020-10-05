/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.dto;

public class HouseDto {
	private String houseName;
	
	public HouseDto(String houseName) {
		this.houseName = houseName;
	}
	
	public String getHouseName() {
		return houseName;
	}
	
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
}
