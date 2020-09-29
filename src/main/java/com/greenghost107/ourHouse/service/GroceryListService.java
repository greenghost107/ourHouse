/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.service;

import com.greenghost107.ourHouse.dto.GroceryDto;
import com.greenghost107.ourHouse.dto.GroceryListDto;
import com.greenghost107.ourHouse.model.Grocery;
import com.greenghost107.ourHouse.model.GroceryList;

public interface GroceryListService {
	GroceryList addGrocery(GroceryListDto groceryListDto,GroceryDto groceryDto);
	
	GroceryList getGroceryListById(GroceryListDto groceryListDto);
}
