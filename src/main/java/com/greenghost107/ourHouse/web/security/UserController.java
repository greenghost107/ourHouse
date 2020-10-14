/*
 * Created by greenghost107 on Oct/2020
 */
package com.greenghost107.ourHouse.web.security;

import com.greenghost107.ourHouse.dto.HouseDto;
import com.greenghost107.ourHouse.exceptions.SpringException;
import com.greenghost107.ourHouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/setHouse", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> setHouseForUser(@RequestHeader("Authorization") String token,@RequestBody HouseDto houseDto) {
		
		return Optional.ofNullable(userService.joinHouse(token,houseDto))
				.map(hous -> new ResponseEntity<>(hous, HttpStatus.OK))
				.orElseThrow(() -> new SpringException("Couldn't create house for user "));
	}
	
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@RequestHeader("Authorization") String token) {

		return Optional.ofNullable(userService.findUserFromToken(token))
		.map(usr -> new ResponseEntity<>(usr, HttpStatus.OK))
				.orElseThrow(() -> new SpringException("Couldn't create house for user "));
	}
	
	
	@RequestMapping(value = "/createHouse", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createHouse(@RequestHeader("Authorization") String token,@RequestBody HouseDto houseDto)  {
		
		return Optional.ofNullable(userService.createHouseForUser(token,houseDto))
				.map(hous -> new ResponseEntity<>(hous, HttpStatus.OK))
				.orElseThrow(() -> new SpringException("Couldn't create house for user "));
		
	}
	
}
