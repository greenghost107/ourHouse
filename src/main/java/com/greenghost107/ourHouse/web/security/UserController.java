/*
 * Created by greenghost107 on Oct/2020
 */
package com.greenghost107.ourHouse.web.security;

import com.greenghost107.ourHouse.config.JwtTokenUtil;
import com.greenghost107.ourHouse.dto.HouseDto;
import com.greenghost107.ourHouse.dto.UserDto;
import com.greenghost107.ourHouse.exceptions.SpringException;
import com.greenghost107.ourHouse.model.House;
import com.greenghost107.ourHouse.model.User;
import com.greenghost107.ourHouse.service.HouseService;
import com.greenghost107.ourHouse.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HouseService houseService;
	
	@RequestMapping(value = "/setHouse", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> setHouseForUser(HttpServletRequest request) throws Exception {
		// From the HttpRequest get the claims

		
		String username = jwtTokenUtil.getUsernameFromToken(request.getHeader("Authorization").substring(7));

		String decReq = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

		JSONObject jsonObject = new JSONObject(decReq);

		JSONObject houseJson= jsonObject.getJSONObject("house");

		
		return Optional.ofNullable(userService.joinHouse(username,houseJson.getString("houseName"),houseJson.getString("housePassword")))
				.map(hous -> new ResponseEntity<>(hous, HttpStatus.OK))
				.orElseThrow(() -> new SpringException("Couldn't create house for user " + username));
	}
	
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(HttpServletRequest request) throws Exception {
		String username = jwtTokenUtil.getUsernameFromToken(request.getHeader("Authorization").substring(7));

		return Optional.ofNullable(userService.findByUserName(username))
				.map(usr -> new ResponseEntity<>(usr, HttpStatus.OK))
				.orElseThrow(() -> new SpringException("Couldn't create house for user " + username));
	}
	
	
	@RequestMapping(value = "/createHouse", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createHouse(HttpServletRequest request) throws Exception {

		
		
		String username = jwtTokenUtil.getUsernameFromToken(request.getHeader("Authorization").substring(7));

		String decReq = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

		JSONObject jsonObject = new JSONObject(decReq);

		JSONObject houseJson= jsonObject.getJSONObject("house");
		
		return Optional.ofNullable(userService.createHouseForUser(username,houseJson.getString("houseName"),houseJson.getString("housePassword")))
				.map(hous -> new ResponseEntity<>(hous, HttpStatus.OK))
				.orElseThrow(() -> new SpringException("Couldn't create house for user " + username));
		
	}
	
}
