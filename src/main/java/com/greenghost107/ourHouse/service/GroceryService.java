/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.service;

import com.greenghost107.ourHouse.model.Grocery;
import com.greenghost107.ourHouse.model.GroceryList;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface GroceryService {
	void removeGroceriesByGroceryListId(Long groceryListId);
	
	Grocery addGrocery(String name, double quantity, GroceryList groceryList);
	
	void deleteGrocery(Grocery grocery);
	
	List<Grocery> getByGroceryListId(Long id);

	 List<Grocery> saveGroceries(List<Grocery> groceries,GroceryList groceryList);
}
