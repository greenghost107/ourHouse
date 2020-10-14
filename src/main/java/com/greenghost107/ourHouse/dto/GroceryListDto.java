/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.dto;

import java.time.LocalDateTime;

public class GroceryListDto {
	
	private Long id;

	private String creatorName;
	
	private LocalDateTime dt_created;

	private String groceryListName;
	
	public GroceryListDto() {
	}
	
	public GroceryListDto(LocalDateTime dt_created) {
		this.dt_created = dt_created;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public LocalDateTime getDt_created() {
		return dt_created;
	}
	
	public void setDt_created(LocalDateTime dt_created) {
		this.dt_created = dt_created;
	}

	public String getGroceryListName() {
		return groceryListName;
	}

	public void setGroceryListName(String groceryListName) {
		this.groceryListName = groceryListName;
	}
}
