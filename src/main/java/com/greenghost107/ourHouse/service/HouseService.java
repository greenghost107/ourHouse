/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.service;

import com.greenghost107.ourHouse.dto.HouseDto;
import com.greenghost107.ourHouse.dto.UserDto;
import com.greenghost107.ourHouse.model.GroceryList;
import com.greenghost107.ourHouse.model.House;
import com.greenghost107.ourHouse.model.User;

import java.util.Set;

public interface HouseService {
	
	House addHouse(House house);
//
//	House addUserToHouse(HouseDto houseDto,String user);
	
	House createNewGroceryList(UserDto userDto,HouseDto houseDto);
	
	Set<User> getUsersForHouse(HouseDto houseDto);
	
	GroceryList getLastGroceryListForHouse(HouseDto houseDto,UserDto userDto);
}
