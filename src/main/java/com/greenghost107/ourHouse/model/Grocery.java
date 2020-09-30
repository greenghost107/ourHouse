/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Grocery implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private double quantity;
	
	private String url;
	
	@JsonBackReference
	@ManyToOne(fetch=FetchType.EAGER,cascade = CascadeType.ALL)

	private GroceryList groceryList;
	
	public Grocery(){}
	
	public Grocery(String name) {
		this.name = name;
		this.quantity = 0;
		this.url = null;
	}
	
	public Grocery(String name, double quantity, String url,GroceryList groceryList) {
		this.name = name;
		this.quantity = quantity;
		this.url = url;
		this.groceryList = groceryList;
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
