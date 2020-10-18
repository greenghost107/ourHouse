/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.service.impl;

import com.greenghost107.ourHouse.dto.GroceryDto;
import com.greenghost107.ourHouse.model.Grocery;
import com.greenghost107.ourHouse.model.GroceryList;
import com.greenghost107.ourHouse.model.House;
import com.greenghost107.ourHouse.repository.GroceryListRepository;
import com.greenghost107.ourHouse.service.GroceryListService;
import com.greenghost107.ourHouse.service.GroceryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class GroceryListServiceImpl implements GroceryListService {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	private GroceryListRepository groceryListRepository;
	
	@Autowired
	private GroceryService groceryService;
	

	@Transactional
	@Override
	public void removeGroceryList(GroceryList groceryList)
	{

		groceryService.removeGroceriesByGroceryListId(groceryList.getId());
		

		groceryListRepository.delete(groceryList);

	}

	@Override
	public void removeGroceryList(Long grocerListId) {
		groceryService.removeGroceriesByGroceryListId(grocerListId);
		groceryListRepository.deleteById(grocerListId);
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
	public List<Grocery> saveGroceries(List<GroceryDto> groceries, Long groceryListId) {
		Optional<GroceryList> optGroceryList = groceryListRepository.findById(groceryListId);
		if (!optGroceryList.isPresent())
		{
			return Collections.EMPTY_LIST;
		}
		GroceryList groceryList = optGroceryList.get();
		return groceryService.saveGroceries(groceries,groceryList);
	}

	
	@Override
	public GroceryList createNewGroceryListForHouse(House house, String creator,String groceryListName) {
		List<GroceryList> groceryLists = house.getGroceryList();
		for(GroceryList groceryList:groceryLists)
		{
			if (groceryList.getGroceryListName().equals(groceryListName))
			{
				LOGGER.error("House Already contains grocery list with the name " + groceryListName);
				return null;
			}
		}

		return groceryListRepository.save(new GroceryList(house,creator,groceryListName));
	}

	@Override
	public List<Grocery> markGroceries(List<Grocery> groceries, Long groceryListId) {
//		Optional<GroceryList> optGroceryList = groceryListRepository.findById(groceryListId);
//		if (!optGroceryList.isPresent())
//		{
//			return Collections.EMPTY_LIST;
//		}
//		GroceryList groceryList = optGroceryList.get();
		return groceryService.markGroceries(groceries,groceryListId);
	}


}
