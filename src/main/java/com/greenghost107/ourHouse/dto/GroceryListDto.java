/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.dto;

import java.time.LocalDateTime;

public class GroceryListDto {
	
	private Long id;
	
	private LocalDateTime dt_created;
	
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
	
	public LocalDateTime getDt_created() {
		return dt_created;
	}
	
	public void setDt_created(LocalDateTime dt_created) {
		this.dt_created = dt_created;
	}
}
