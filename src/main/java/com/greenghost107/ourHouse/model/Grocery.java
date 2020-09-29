/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "grocery")
public class Grocery implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private double quantity;
	
	private String url;
	
	public Grocery(){}
	
	public Grocery(String name) {
		this.name = name;
		this.quantity = 0;
		this.url = null;
	}
	
	public Grocery(String name, double quantity, String url) {
		this.name = name;
		this.quantity = quantity;
		this.url = url;
	}
	
	public Long getId() {
		return id;
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
