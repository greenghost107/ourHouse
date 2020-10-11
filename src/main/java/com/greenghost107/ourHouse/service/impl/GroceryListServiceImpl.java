/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.service.impl;

import com.greenghost107.ourHouse.dto.GroceryDto;
import com.greenghost107.ourHouse.dto.GroceryListDto;
import com.greenghost107.ourHouse.model.Grocery;
import com.greenghost107.ourHouse.model.GroceryList;
import com.greenghost107.ourHouse.model.House;
import com.greenghost107.ourHouse.repository.GroceryListRepository;
import com.greenghost107.ourHouse.service.GroceryListService;
import com.greenghost107.ourHouse.service.GroceryService;
import com.greenghost107.ourHouse.service.HttpServletRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
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
		Grocery grocery = groceryService.addGrocery(groceryDto.getName(),groceryDto.getQuantity(),groceryList);
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
	
	@Override
	public List<GroceryList> getGroceryListByHouseId(Long houseId) {
		List<GroceryList> groceries = groceryListRepository.findGroceryListByHouseId(houseId);
		return (groceries==null?Collections.EMPTY_LIST:groceries);
	}
	
	public GroceryList saveGroceryList(GroceryList groceryList)
	{
		return groceryListRepository.save(groceryList);
	}
	
	@Override
	public List<Grocery> saveGroceryList(HttpServletRequest request, Long groceryListId) {
		Optional<GroceryList> optGroceryList = groceryListRepository.findById(groceryListId);
		if (!optGroceryList.isPresent())
		{
			return Collections.EMPTY_LIST;
		}
		GroceryList groceryList = optGroceryList.get();
		return groceryService.saveGroceries(request,groceryList);
	}
	
	@Override
	public GroceryList createNewGroceryListForHouse(House house, String creator) {
		return groceryListRepository.save(new GroceryList(house,creator));
	}
	
}
