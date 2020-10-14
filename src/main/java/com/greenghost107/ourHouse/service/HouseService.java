/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.service;

import com.greenghost107.ourHouse.dto.GroceryListDto;
import com.greenghost107.ourHouse.dto.HouseDto;
import com.greenghost107.ourHouse.dto.UserDto;
import com.greenghost107.ourHouse.model.GroceryList;
import com.greenghost107.ourHouse.model.House;
import com.greenghost107.ourHouse.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface HouseService {
	
	House addHouse(House house);
	
	boolean validatePassword(House house, String password);
	
	User addUserToHouse(House house,User joiningUser);
	
	Optional<House> findByHouseName(String houseName);


	House getHouseForUser(String token);

	GroceryList createNewGroceryList(String token, Long houseId , GroceryListDto groceryListDto);
}
