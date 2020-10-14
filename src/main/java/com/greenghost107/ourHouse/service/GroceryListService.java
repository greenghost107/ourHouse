/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.service;

import com.greenghost107.ourHouse.dto.GroceryDto;
import com.greenghost107.ourHouse.dto.GroceryListDto;
import com.greenghost107.ourHouse.model.Grocery;
import com.greenghost107.ourHouse.model.GroceryList;
import com.greenghost107.ourHouse.model.House;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface GroceryListService {

	void removeGroceryList(GroceryList groceryList);
	
	List<GroceryList> getGroceryListByHouseId(Long houseId);
	
	GroceryList saveGroceryList(GroceryList groceryList);

	List<Grocery> saveGroceries(List<Grocery> groceries,Long groceryListId);

	GroceryList createNewGroceryListForHouse(House house, String creator,String groceryListName);
}
