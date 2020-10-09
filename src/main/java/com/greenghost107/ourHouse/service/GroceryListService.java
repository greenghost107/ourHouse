/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.service;

import com.greenghost107.ourHouse.dto.GroceryDto;
import com.greenghost107.ourHouse.dto.GroceryListDto;
import com.greenghost107.ourHouse.model.GroceryList;
import com.greenghost107.ourHouse.model.House;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GroceryListService {
	GroceryList addGrocery(GroceryListDto groceryListDto,GroceryDto groceryDto);
	
	GroceryList getGroceryListById(GroceryListDto groceryListDto);
	
	void removeGroceryList(GroceryList groceryList);
	
	List<GroceryList> getGroceryListByHouseId(Long houseId);
	
	GroceryList saveGroceryList(GroceryList groceryList);
}
