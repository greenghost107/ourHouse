/*
 * Created by greenghost107 on Oct/2020
 */
package com.greenghost107.ourHouse.web.security;

import com.greenghost107.ourHouse.config.JwtTokenUtil;
import com.greenghost107.ourHouse.dto.HouseDto;
import com.greenghost107.ourHouse.exceptions.SpringException;
import com.greenghost107.ourHouse.model.GroceryList;
import com.greenghost107.ourHouse.model.House;
import com.greenghost107.ourHouse.service.GroceryListService;
import com.greenghost107.ourHouse.service.HouseService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/house")
public class HouseController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private HouseService houseService;
	
	@Autowired
	private GroceryListService groceryListService;
	
	@RequestMapping(value = "/getHouse" , method = RequestMethod.GET)
	public ResponseEntity<?> getHouse(HttpServletRequest request)throws Exception
	{
		String username = jwtTokenUtil.getUsernameFromToken(request.getHeader("Authorization").substring(7));
		
		return Optional.ofNullable(houseService.getHouseForUser(username))
				.map(hous -> new ResponseEntity<>(hous, HttpStatus.OK))
				.orElseThrow(() -> new SpringException("Can't find house for user " + username));
	}
	
	@RequestMapping(value = "/getGroceryLists/{houseId}", method = RequestMethod.GET)
	public ResponseEntity<?> getGroceryListsForHouse(@PathVariable( "houseId" ) Long houseId,HttpServletRequest request)throws Exception
	{
		return Optional.ofNullable(groceryListService.getGroceryListByHouseId(houseId))
				.map(hous -> new ResponseEntity<>(hous, HttpStatus.OK))
				.orElseThrow(() -> new SpringException("Couldn't get GroceryLists for house " + houseId));
		
	}
	
	@RequestMapping(value = "/createNewGroceryList", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createNewGroceryList(HttpServletRequest request) throws Exception {
		
		String userName = jwtTokenUtil.getUsernameFromToken(request.getHeader("Authorization").substring(7));

		String decReq = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

		JSONObject jsonObject = new JSONObject(decReq);

		JSONObject houseJson= jsonObject.getJSONObject("house");

		HouseDto houseDto = new HouseDto(new Long((Integer)houseJson.get("id")),houseJson.getString("houseName"),houseJson.getString("housePassword"));
		return Optional.ofNullable(houseService.createNewGroceryList(houseDto,userName))
				.map(hous -> new ResponseEntity<>(hous, HttpStatus.OK))
				.orElseThrow(() -> new SpringException("Couldn't create GroceryList for house " + houseDto.getId()));
	}
}
