/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.service;

import com.greenghost107.ourHouse.model.Grocery;
import com.greenghost107.ourHouse.repository.GroceryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroceryServiceImpl implements GroceryService {
	@Autowired
	private GroceryRepository groceryRepository;
	
	@Override
	public Grocery addGrocery(String groceryName)
	{
		return groceryRepository.save(new Grocery(groceryName));
	}
}
