/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.web;

import com.greenghost107.ourHouse.dto.HouseDto;
import com.greenghost107.ourHouse.dto.UserDto;
import com.greenghost107.ourHouse.exceptions.SpringException;
import com.greenghost107.ourHouse.model.User;
import com.greenghost107.ourHouse.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> addUser(final UserDto userDto) {
		LOGGER.info("add user");
		//TODO michael - remove the user finding
		User user = new User(userDto.getUserName());
		return Optional.ofNullable(userService.saveUser(user))
				.map(usr -> new ResponseEntity<>(usr, HttpStatus.OK))
				.orElseThrow(() -> new SpringException("Can't add User"));
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers(){
		LOGGER.info("get All Users");
		return Optional.ofNullable(userService.findAllUsers())
				.map(cour -> new ResponseEntity<>(cour, HttpStatus.OK))
				.orElseThrow(() -> new SpringException("User DataBase is empty"));
	}
	
	
	@RequestMapping(value = "/joinUserToHouse",method = RequestMethod.POST)
	public ResponseEntity<User> joinUserToHouse(UserDto userDto,HouseDto houseDto) {
		LOGGER.info("create House " + houseDto.getHouseName() + " By User " + userDto.getUserName());
		return Optional.ofNullable(userService.joinUserToHouse(userDto,houseDto))
				.map(usr -> new ResponseEntity<>(usr, HttpStatus.OK))
				.orElseThrow(() -> new SpringException("Can't add User to House"));
	}
	

	
	
}
