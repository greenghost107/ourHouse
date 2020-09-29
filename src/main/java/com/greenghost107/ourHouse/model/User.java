/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	
	@JsonBackReference
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="house_id",referencedColumnName="id")
	private House house;
	
	public User() {
	}
	
	public User(String username) {
		this.username = username;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public House getHouse() {
		return house;
	}
	
	public void setHouse(House house) {
		this.house = house;
	}
}
