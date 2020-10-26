/*
 * Created by greenghost107 on Oct/2020
 */
package com.greenghost107.ourHouse.web.security;

import com.greenghost107.ourHouse.dto.GroceryDto;
import com.greenghost107.ourHouse.exceptions.SpringException;
import com.greenghost107.ourHouse.model.Grocery;
import com.greenghost107.ourHouse.service.GroceryListService;
import com.greenghost107.ourHouse.service.GroceryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/groceryList")
public class GroceryListController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	private GroceryListService groceryListService;
	
	@Autowired
	private GroceryService groceryService;
	
	@RequestMapping(value = "/{groceryListId}", method = RequestMethod.GET)
	public ResponseEntity<?> getGroceryListsForHouse(@PathVariable( "groceryListId" ) Long groceryListId)
	{
		LOGGER.debug("getGroceryListsForHouse " + groceryListId);
		return Optional.ofNullable(groceryService.getByGroceryListId(groceryListId))
				.map(grocLst -> new ResponseEntity<>(grocLst, HttpStatus.OK))
				.orElseThrow(() -> new SpringException("Couldn't get groceries for grocerylist with id +" + groceryListId));
		
	}

	@RequestMapping(value = "/saveGroceryList/{groceryListId}", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveGroceryList(@PathVariable( "groceryListId" ) Long groceryListId, @RequestBody List<GroceryDto> groceries) {
		LOGGER.debug("saveGroceryList " + groceryListId);
		return Optional.ofNullable(groceryListService.saveGroceries(groceries,groceryListId))
				.map(hous -> new ResponseEntity<>(hous, HttpStatus.OK))
				.orElseThrow(() -> new SpringException("can't save the grocerylist"));
	}
	@RequestMapping(value = "/deleteGroceryList/{groceryListId}", method = RequestMethod.DELETE)
	public void deleteGroceryList(@PathVariable( "groceryListId" ) Long groceryListId) {
		LOGGER.debug("deleteGroceryList " + groceryListId);
		groceryListService.removeGroceryList(groceryListId);
	}

	@RequestMapping(value = "/markGroceries/{groceryListId}", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> markGroceries(@PathVariable( "groceryListId" ) Long groceryListId, @RequestBody List<Grocery> groceries) {
		LOGGER.debug("markGroceries in groceryList " + groceryListId);
		return Optional.ofNullable(groceryListService.markGroceries(groceries,groceryListId))
				.map(groc -> new ResponseEntity<>(groc, HttpStatus.OK))
				.orElseThrow(() -> new SpringException("can't update grocerylist " + groceryListId));
	}

}
