/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.web;

import com.greenghost107.ourHouse.dto.GroceryDto;
import com.greenghost107.ourHouse.dto.GroceryListDto;
import com.greenghost107.ourHouse.exceptions.SpringException;
import com.greenghost107.ourHouse.model.GroceryList;
import com.greenghost107.ourHouse.service.GroceryListService;
import com.greenghost107.ourHouse.service.GroceryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/groceries")
public class GroceryListController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private GroceryListService groceryListService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<GroceryList> getGroceryListById(GroceryListDto groceryListDto)
	{
		LOGGER.info("get groceryList by Id " + groceryListDto.getId());
		return Optional.ofNullable(groceryListService.getGroceryListById(groceryListDto))
				.map(grol -> new ResponseEntity<>(grol, HttpStatus.OK))
				.orElseThrow(() -> new SpringException("No GroceryLists Found"));
	}
	
	@RequestMapping(value = "/addGrocery",method = RequestMethod.POST)
	public ResponseEntity<GroceryList> addGroceryToList(GroceryListDto groceryListDto, GroceryDto groceryDto)
	{
		LOGGER.info("add grocery to groceryList " + groceryListDto.getId());
		return Optional.ofNullable(groceryListService.addGrocery(groceryListDto,groceryDto))
				.map(grol -> new ResponseEntity<>(grol, HttpStatus.OK))
				.orElseThrow(() -> new SpringException("Couldn't add grocery " + groceryDto.getName() + " to list " + groceryListDto.getId()));
	}
}
