/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.web;

import com.greenghost107.ourHouse.dto.HouseDto;
import com.greenghost107.ourHouse.dto.UserDto;
import com.greenghost107.ourHouse.exceptions.SpringException;
import com.greenghost107.ourHouse.model.GroceryList;
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

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/house")
public class HouseController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private HouseService houseService;
	
//	@RequestMapping(method = RequestMethod.POST)
//	public ResponseEntity<House> addHouse(final HouseDto houseDto) {
//		LOGGER.info("add house");
//		House house = new House(houseDto.getHouseName());
//		return Optional.ofNullable(houseService.addHouse(house))
//				.map(hous -> new ResponseEntity<>(hous, HttpStatus.OK))
//				.orElseThrow(() -> new SpringException("Can't add House"));
//	}
	
//	@RequestMapping(value = "/addUser",method = RequestMethod.POST)
//	public ResponseEntity<House> addUserToHouse(final HouseDto houseDto,final UserDto userDto) {
//		LOGGER.info("add user to house");
////		House house = new House(houseDto.getHouseName());
//		return Optional.ofNullable(houseService.addUserToHouse(houseDto,userDto.getUserName()))
//				.map(hous -> new ResponseEntity<>(hous, HttpStatus.OK))
//				.orElseThrow(() -> new SpringException("Can't add User to House"));
//	}
	
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
		return Optional.ofNullable(houseService.createNewGroceryList(userDto,houseDto))
				.map(hous -> new ResponseEntity<>(hous, HttpStatus.OK))
				.orElseThrow(() -> new SpringException("Can't add Student"));
	}
	
	@RequestMapping(value = "/getLastGroceryListForHouse",method = RequestMethod.GET)
	public ResponseEntity<GroceryList> getLastGroceryListForHouse(HouseDto houseDto,UserDto userDto)
	{
		LOGGER.info("get Last groceryList for house: " + houseDto.getHouseName());
		return Optional.ofNullable(houseService.getLastGroceryListForHouse(houseDto,userDto))
				.map(hous -> new ResponseEntity<>(hous, HttpStatus.OK))
				.orElseThrow(() -> new SpringException("Can't add Student"));
	}
	
}
