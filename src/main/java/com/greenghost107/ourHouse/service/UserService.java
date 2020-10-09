/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.service;

import com.greenghost107.ourHouse.dto.HouseDto;
import com.greenghost107.ourHouse.dto.UserDto;
import com.greenghost107.ourHouse.model.House;
import com.greenghost107.ourHouse.model.User;

import java.util.List;

public interface UserService {
	
	User saveUser(UserDto userDto);
	
	User findByUserName(String userName);
	
	List<User> findAllUsers();
	
	User joinUserToHouse(UserDto userDto,HouseDto houseDto);
	
	House getHouseForUser();
	
	House createHouseForUser(String userName,String houseName,String housePassword);
	
	House joinHouse(String joiningUserName,String  houseName,String password);
}
