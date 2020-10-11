/*
 * Created by greenghost107 on Oct/2020
 */
package com.greenghost107.ourHouse.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenghost107.ourHouse.config.JwtTokenUtil;
import com.greenghost107.ourHouse.model.Grocery;
import com.greenghost107.ourHouse.model.House;
import com.greenghost107.ourHouse.model.User;
import com.greenghost107.ourHouse.repository.HouseRepository;
import com.greenghost107.ourHouse.service.HttpServletRequestService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HttpServletRequestImpl implements HttpServletRequestService {
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private HouseRepository houseRepository;
	
	
	@Override
	public String getUserNameFromRequest(HttpServletRequest request) {
		return jwtTokenUtil.getUsernameFromToken(request.getHeader("Authorization").substring(7));
	}
	@Override
	public Optional<House> getHouseFromJson(HttpServletRequest request)
	{
		try{
			String decReq = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			
			JSONObject jsonObject = new JSONObject(decReq);
			
			JSONObject houseJson= jsonObject.getJSONObject("house");
			
			return houseRepository.findByHouseName(houseJson.getString("houseName"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}
	
	@Override
	public House createNewHouseFromJson(HttpServletRequest request, User user) {
		try{
			String decReq = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			
			JSONObject jsonObject = new JSONObject(decReq);
			
			JSONObject houseJson= jsonObject.getJSONObject("house");
			
			return new House(houseJson.getString("houseName"),houseJson.getString("housePassword"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Grocery> getArrayOfGroceries(HttpServletRequest request) {
		List<Grocery> returnedList = new ArrayList<>();
		try{
			
			String decReq = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			
			JSONObject jsonObject = new JSONObject(decReq);
			ObjectMapper mapper = new ObjectMapper();
			JSONArray jsonArray = jsonObject.getJSONArray("groceryList");
			Grocery[] groceries = mapper.readValue(jsonArray.toString(),Grocery[].class);
			return Arrays.asList(groceries);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnedList;
	}
}
