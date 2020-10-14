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

public interface UserService {

	User findByUserName(String userName);
	
	House getHouseForUser();
	
	House createHouseForUser(String userName,String houseName,String housePassword);

	House joinHouse(String joiningUserName,String  houseName,String password);

	House createHouseForUser(String token, HouseDto houseDto);

	User findUserFromToken(String token);

	House joinHouse(String token, HouseDto houseDto);
}
