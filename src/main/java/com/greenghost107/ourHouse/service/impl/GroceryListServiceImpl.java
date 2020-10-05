/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.service.impl;

import com.greenghost107.ourHouse.dto.GroceryDto;
import com.greenghost107.ourHouse.dto.GroceryListDto;
import com.greenghost107.ourHouse.model.Grocery;
import com.greenghost107.ourHouse.model.GroceryList;
import com.greenghost107.ourHouse.repository.GroceryListRepository;
import com.greenghost107.ourHouse.service.GroceryListService;
import com.greenghost107.ourHouse.service.GroceryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class GroceryListServiceImpl implements GroceryListService {
	
	@Autowired
	private GroceryListRepository groceryListRepository;
	
	@Autowired
	private GroceryService groceryService;
	
	@Override
	public GroceryList addGrocery(GroceryListDto groceryListDto,GroceryDto groceryDto)
	{
//		Grocery grocery = new Grocery(groceryDto.getName(),groceryDto.getQuantity(),groceryDto.getUrl());
		
		Optional<GroceryList> optgroceryList = groceryListRepository.findById(groceryListDto.getId());
		if(!optgroceryList.isPresent())
			return null;
		GroceryList groceryList = optgroceryList.get();
		Grocery grocery = groceryService.addGrocery(groceryDto.getName(),groceryDto.getQuantity(),groceryDto.getUrl(),groceryList);
		groceryList.addGroceryToList(grocery);
		groceryListRepository.save(groceryList);
		
		
		return groceryList;
	}
	
	@Override
	public GroceryList getGroceryListById(GroceryListDto groceryListDto)
	{
		Optional<GroceryList> optGroceryList = groceryListRepository.findById(groceryListDto.getId());
		return (optGroceryList.isPresent()?optGroceryList.get():null);
	}
	@Transactional
	@Override
	public void removeGroceryList(GroceryList groceryList)
	{

		groceryService.removeGroceriesByGroceryListId(groceryList.getId());
		

		groceryListRepository.delete(groceryList);

	}
}
