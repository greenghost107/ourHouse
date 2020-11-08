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
	@Column(unique = true)
	private String houseName;
	
	private String housePassword;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "owner_id", referencedColumnName = "id")
//	//@OneToOne(targetEntity = User.class, cascade = CascadeType.ALL)
//	private User owner;
	
	@JsonBackReference("house_users")
	@OneToMany(mappedBy="house",cascade=CascadeType.ALL)
	private Set<User> users;
	
	@JsonBackReference(value = "house_groceryList")
	@OneToMany(mappedBy="house",cascade=CascadeType.ALL)
	private List<GroceryList> groceryList;

	@JsonBackReference(value = "house_expense")
	@OneToMany(mappedBy="house",cascade=CascadeType.ALL)
	private List<Expense> expenses;
	
	public House(){}
	
//	public House(String name) {
//		this.houseName = name;
//		this.users = new HashSet<>();
//		this.groceryList = new ArrayList<>();
//	}
public House(String name,String housePassword, User user) {
	this.houseName = name;
	this.users = new HashSet<>();
	this.users.add(user);
	this.housePassword = housePassword;
	this.groceryList = new ArrayList<>();
}
	
	public House(String houseName, String housePassword) {
		this.houseName = houseName;
		this.housePassword = housePassword;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	
	public String getHouseName() {
		return houseName;
	}
	
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	
	public Set<User> getUsers() {
		return users;
	}
	
	public boolean addUser(User user) {
		return users.add(user);
	}
	
	public boolean createNewGroceryList(String creatorName,String groceryListName)
	{
		if (this.groceryList == null)
		{
			this.groceryList = new ArrayList<>();
		}
		return groceryList.add(new GroceryList(this,creatorName,groceryListName));
	}
	
	public List<GroceryList> getGroceryList() {
		return groceryList;
	}
	
	public GroceryList getLastGroceryList(String creatorName)
	{
		
		return (groceryList.size()>0?groceryList.get(groceryList.size()-1):null);
	}
	
	public String getHousePassword() {
		return housePassword;
	}
	
	public void setHousePassword(String housePassword) {
		this.housePassword = housePassword;
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}
}
