/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.service;

import com.greenghost107.ourHouse.dto.GroceryDto;
import com.greenghost107.ourHouse.dto.GroceryListDto;
import com.greenghost107.ourHouse.model.Grocery;
import com.greenghost107.ourHouse.model.GroceryList;
import com.greenghost107.ourHouse.repository.GroceryListRepository;
import com.greenghost107.ourHouse.repository.GroceryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroceryListServiceImpl implements GroceryListService {
	
	@Autowired
	private GroceryListRepository groceryListRepository;
	
	@Autowired
	private GroceryRepository groceryRepository;
	
	@Autowired
	private GroceryService groceryService;
	
	@Override
	public GroceryList addGrocery(GroceryListDto groceryListDto,GroceryDto groceryDto)
	{
		Grocery grocery = groceryRepository.findGroceryByName(groceryDto.getName());
		
		if(grocery == null)
		{
			//add grocery to db
			grocery = groceryService.addGrocery(groceryDto.getName());
		}
		Optional<GroceryList> optgroceryList = groceryListRepository.findById(groceryListDto.getId());
		if(!optgroceryList.isPresent())
			return null;
		GroceryList groceryList = optgroceryList.get();
		groceryList.addGroceryToList(grocery);
		return groceryListRepository.save(groceryList);
	}
	
	@Override
	public GroceryList getGroceryListById(GroceryListDto groceryListDto)
	{
		Optional<GroceryList> optGroceryList = groceryListRepository.findById(groceryListDto.getId());
		return (optGroceryList.isPresent()?optGroceryList.get():null);
	}
}
