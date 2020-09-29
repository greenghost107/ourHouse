/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.service;

import com.greenghost107.ourHouse.dto.HouseDto;
import com.greenghost107.ourHouse.dto.UserDto;
import com.greenghost107.ourHouse.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
	
	User saveUser(User user);
	
	User findByUserName(String userName);
	
	List<User> findAllUsers();
	
	User joinUserToHouse(UserDto userDto,HouseDto houseDto);
	
	
}
