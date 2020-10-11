/*
 * Created by greenghost107 on Oct/2020
 */
package com.greenghost107.ourHouse.web.security;

import com.greenghost107.ourHouse.exceptions.SpringException;
import com.greenghost107.ourHouse.service.GroceryListService;
import com.greenghost107.ourHouse.service.GroceryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@RequestMapping("/groceryList")
public class GroceryListController {
	
	@Autowired
	private GroceryListService groceryListService;
	
	@Autowired
	private GroceryService groceryService;
	
	@RequestMapping(value = "/{groceryListId}", method = RequestMethod.GET)
	public ResponseEntity<?> getGroceryListsForHouse(@PathVariable( "groceryListId" ) Long groceryListId)
	{
		return Optional.ofNullable(groceryService.getByGroceryListId(groceryListId))
				.map(grocLst -> new ResponseEntity<>(grocLst, HttpStatus.OK))
				.orElseThrow(() -> new SpringException("Couldn't get groceries for grocerylist with id +" + groceryListId));
		
	}
	
	@RequestMapping(value = "/saveGroceryList/{groceryListId}", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveGroceryList(@PathVariable( "groceryListId" ) Long groceryListId,HttpServletRequest request) {
		
		return Optional.ofNullable(groceryListService.saveGroceryList(request,groceryListId))
				.map(hous -> new ResponseEntity<>(hous, HttpStatus.OK))
				.orElseThrow(() -> new SpringException("can't save the grocerylist"));
	}
	
}