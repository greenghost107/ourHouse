/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.service.impl;

import com.greenghost107.ourHouse.model.Grocery;
import com.greenghost107.ourHouse.model.GroceryList;
import com.greenghost107.ourHouse.repository.GroceryRepository;
import com.greenghost107.ourHouse.service.GroceryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class GroceryServiceImpl implements GroceryService {
	
	@Autowired
	private GroceryRepository groceryRepository;
	
	
	@Override
	public void removeGroceriesByGroceryListId(Long groceryListId) {
		groceryRepository.deleteAllByGroceryListId(groceryListId);
	}
	
	@Override
	public Grocery addGrocery(String name, double quantity, String url,GroceryList groceryList) {
		return groceryRepository.save(new Grocery(name, quantity, url,groceryList));
	}
	@Transactional
	@Override
	public void deleteGrocery(Grocery grocery) {
		groceryRepository.delete(grocery);

	}
}
