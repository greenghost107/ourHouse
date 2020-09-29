/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.dto;

import com.greenghost107.ourHouse.model.GroceryList;

import javax.persistence.*;

public class GroceryDto {
	
	private String name;
	
	private double quantity;
	
	private String url;
	
	
	public GroceryDto(String name, double quantity, String url) {
		this.name = name;
		this.quantity = quantity;
		this.url = url;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getQuantity() {
		return quantity;
	}
	
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
}
