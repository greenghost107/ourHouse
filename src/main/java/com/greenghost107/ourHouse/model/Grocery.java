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

	private Boolean isMarked;
	
	
	@JsonBackReference(value = "groceryList-id")
	@ManyToOne(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	private GroceryList groceryList;
	
	public Grocery(){}
	
	public Grocery(String name) {
		this.name = name;
		this.quantity = 0;
		this.isMarked = Boolean.FALSE;
	}
	
	public Grocery(String name, double quantity,boolean isMarked) {
		this.name = name;
		this.quantity = quantity;
		this.isMarked = isMarked;
	}
	
	public Grocery(String name, double quantity, GroceryList groceryList) {
		this.name = name;
		this.quantity = quantity;
		this.groceryList = groceryList;
		this.isMarked = Boolean.FALSE;
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

	public Boolean getMarked() {
		return isMarked;
	}

	public void setMarked(Boolean marked) {
		isMarked = marked;
	}

	public GroceryList getGroceryList() {
		return groceryList;
	}
	
	public void setGroceryList(GroceryList groceryList) {
		this.groceryList = groceryList;
	}

}
