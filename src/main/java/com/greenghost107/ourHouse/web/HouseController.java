/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.web;

import com.greenghost107.ourHouse.dto.HouseDto;
import com.greenghost107.ourHouse.dto.UserDto;
import com.greenghost107.ourHouse.exceptions.SpringException;
import com.greenghost107.ourHouse.model.House;
import com.greenghost107.ourHouse.model.User;
import com.greenghost107.ourHouse.service.HouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/house")
public class HouseController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private HouseService houseService;
	
	@RequestMapping(value = "/findUsersForHouse", method = RequestMethod.GET)
	public ResponseEntity<Set<User>> findUsersForHouse(HouseDto houseDto)
	{
		LOGGER.info("find All Users in house " + houseDto.getHouseName());
		return Optional.ofNullable(houseService.getUsersForHouse(houseDto))
				.map(hous -> new ResponseEntity<>(hous, HttpStatus.OK))
				.orElseThrow(() -> new SpringException("Can't add User to House"));
	}
	
	@RequestMapping(value = "/createNewGroceryList",method = RequestMethod.POST)
	public ResponseEntity<House> createNewShoppingList(UserDto userDto ,HouseDto houseDto) {
		LOGGER.info("create new shopping list");
		//if house field is empty nukk pointer exception
		return Optional.ofNullable(houseService.createNewGroceryList(userDto,houseDto))
				.map(hous -> new ResponseEntity<>(hous, HttpStatus.OK))
				.orElseThrow(() -> new SpringException("Can't add House"));
	}
	
	@RequestMapping(value = "/getAllGroceryListNamesForHouse",method = RequestMethod.GET)
	public ResponseEntity<List<String>> getAllGroceryListNamesForHouse(HouseDto houseDto)
	{
		LOGGER.info("get All Grocery List Names For House " + houseDto.getHouseName());
		return Optional.ofNullable(houseService.getAllGroceryListNamesForHouse(houseDto))
				.map(hous -> new ResponseEntity<>(hous, HttpStatus.OK))
				.orElseThrow(() -> new SpringException("Can't add House"));
	}
	
	
	@RequestMapping(value = "/removeGroceryListForHouseById",method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> removeGroceryListForHouseById(HouseDto houseDto,Long listId)
	{
		LOGGER.info("get All Grocery List Names For House " + houseDto.getHouseName());
		return Optional.ofNullable(houseService.removeGroceryListForHouseByName(houseDto,listId))
				.map(hous -> new ResponseEntity<>(hous, HttpStatus.OK))
				.orElseThrow(() -> new SpringException("Couldn't remove list with name " + listId));
	}
	
	
	
}
