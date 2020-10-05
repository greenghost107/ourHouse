/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
	@JoinColumn(name="house_id",referencedColumnName="id")
	private House house;
	
	
	
//	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
//	@JoinColumn(name = "Grocery", referencedColumnName = "id", nullable = false)
//	@ElementCollection
//	@Embedded
//	@CollectionTable(name = "GroceryListGroceries", joinColumns = @JoinColumn(name = "groceryListId"))
//	@JoinColumn(name = "groceryListId")
//	@OnDelete(action= OnDeleteAction.CASCADE)
//	@AttributeOverrides({
//			@AttributeOverride(name = "name", column = @Column(name = "name")),
//			@AttributeOverride(name = "quantity", column = @Column(name = "quantity")),
//			@AttributeOverride(name = "url", column = @Column(name = "url"))
//	})
	@JsonBackReference
	@OneToMany(mappedBy="groceryList", cascade={CascadeType.ALL})
	//The mappedBy reference indicates "Go look over on the bean property named 'customer' on the thing I have a collection of to find the configuration."
	private List<Grocery> groceries;
	
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
		return groceries.add(grocery);
	}
	
	public String getCreatorName() {
		return creatorName;
	}
	
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	
	public List<Grocery> getGroceries() {
		return groceries;
	}
}

