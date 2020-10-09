/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.service;

import com.greenghost107.ourHouse.dto.HouseDto;
import com.greenghost107.ourHouse.dto.UserDto;
import com.greenghost107.ourHouse.model.House;
import com.greenghost107.ourHouse.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

public interface HouseService {
	
	House addHouse(House house);
	
//	House createNewGroceryList(UserDto userDto,HouseDto houseDto);
	
	House createNewGroceryList(HttpServletRequest request);
	
	House createNewGroceryList(HouseDto houseDto,String creatorName);
	
	Set<User> getUsersForHouse(HouseDto houseDto);
	
	
	List<String> getAllGroceryListNamesForHouse(HouseDto houseDto);
	
	Boolean removeGroceryListForHouseByName(HouseDto houseDto,Long listId);
	
	House getHouseForUser(HttpServletRequest request);
	
	House getHouseForUser(String userName);
	
	boolean validatePassword(House house, String password);
	
	House addUserToHouse(House house,User joiningUser);
	
	
}
