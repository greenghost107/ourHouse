/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "groceryList")
public class GroceryList implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime dt_created;
	
	private String creatorName;
	
	@JsonBackReference
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="house_id",referencedColumnName="id",unique=true)
	private House house;
	
	@JsonManagedReference
	@OneToMany
	//The mappedBy reference indicates "Go look over on the bean property named 'customer' on the thing I have a collection of to find the configuration."
	private List<Grocery> grocerys;
	
	public GroceryList(){}
	
	public GroceryList(House house,String creatorName) {
		this.house = house;
		this.dt_created = LocalDateTime.now();
		this.creatorName = creatorName;
	}
	
	public GroceryList(Long id, LocalDateTime dt_created, House house) {
		this.id = id;
		this.dt_created = dt_created;
		this.house = house;
	}
	
	public Long getId() {
		return id;
	}
	
	public LocalDateTime getDt_created() {
		return dt_created;
	}
	
	public boolean addGroceryToList(Grocery grocery)
	{
		return grocerys.add(grocery);
	}
	
	public String getCreatorName() {
		return creatorName;
	}
	
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
}

