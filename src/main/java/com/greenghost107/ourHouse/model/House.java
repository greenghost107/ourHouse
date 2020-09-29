/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "house")
public class House {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String houseName;
	
	@JsonBackReference
	@OneToMany(mappedBy="house",cascade=CascadeType.ALL)
	private Set<User> users;
	
	@JsonBackReference
	@OneToMany(mappedBy="house",cascade=CascadeType.ALL)
	private List<GroceryList> groceryList;
	
	public House(){}
	
	public House(String name) {
		this.houseName = name;
		this.users = new HashSet<>();
		this.groceryList = new ArrayList<>();
	}
	
	
	public String getName() {
		return houseName;
	}
	
	public void setName(String name) {
		this.houseName = name;
	}
	
	public Set<User> getUsers() {
		return users;
	}
	
	public boolean addUser(User user) {
		return users.add(user);
	}
	
	public boolean createNewGroceryList(String creatorName)
	{
		if (this.groceryList == null)
		{
			this.groceryList = new ArrayList<>();
		}
		return groceryList.add(new GroceryList(this,creatorName));
	}
	
	public List<GroceryList> getGroceryList() {
		return groceryList;
	}
	
	public GroceryList getLastGroceryList(String creatorName)
	{
		
		return (groceryList.size()>0?groceryList.get(groceryList.size()-1):null);
	}
}
