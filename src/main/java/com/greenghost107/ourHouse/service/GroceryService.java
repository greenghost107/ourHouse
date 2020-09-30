/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.service;

import com.greenghost107.ourHouse.model.Grocery;
import com.greenghost107.ourHouse.model.GroceryList;

public interface GroceryService {
	void removeGroceriesByGroceryListId(Long groceryListId);
	
	Grocery addGrocery(String name, double quantity, String url,GroceryList groceryList);
	
	void deleteGrocery(Grocery grocery);
}
