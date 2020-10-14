/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.dto;

public class HouseDto {
	private Long id;
	private String houseName;
	private String housePassword;

	public HouseDto() {
	}

	public HouseDto(Long id, String houseName, String housePassword) {
		this.id = id;
		this.houseName = houseName;
		this.housePassword = housePassword;
	}
	
	public HouseDto(String houseName,String housePassword) {
		this.houseName = houseName;
		this.housePassword = housePassword;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getHouseName() {
		return houseName;
	}
	
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	
	public String getHousePassword() {
		return housePassword;
	}
	
	public void setHousePassword(String housePassword) {
		this.housePassword = housePassword;
	}
}
