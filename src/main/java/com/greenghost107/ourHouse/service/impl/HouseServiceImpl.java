/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.service.impl;

import com.greenghost107.ourHouse.config.JwtTokenUtil;
import com.greenghost107.ourHouse.dto.HouseDto;
import com.greenghost107.ourHouse.dto.UserDto;
import com.greenghost107.ourHouse.model.GroceryList;
import com.greenghost107.ourHouse.model.House;
import com.greenghost107.ourHouse.model.User;
import com.greenghost107.ourHouse.repository.HouseRepository;
import com.greenghost107.ourHouse.repository.UserRepository;
import com.greenghost107.ourHouse.service.GroceryListService;
import com.greenghost107.ourHouse.service.HouseService;
import com.greenghost107.ourHouse.service.HttpServletRequestService;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class HouseServiceImpl implements HouseService {
	private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	private HouseRepository houseRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private GroceryListService groceryListService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private HttpServletRequestService httpServletRequestService;
	
	@Override
	public House addHouse(House house) {
		return houseRepository.save(house);
	}
	
	@Override
	public House createNewGroceryList(HttpServletRequest request) {
		String userName = httpServletRequestService.getUserNameFromRequest(request);
		
		Optional<House> optHouse = httpServletRequestService.getHouseFromJson(request);
		
		if (!optHouse.isPresent() && !optHouse.get().createNewGroceryList(userName))
		{
			return null;
		}
		return houseRepository.save(optHouse.get());
		
	}

	
	@Override
	public House createNewGroceryList(HouseDto houseDto,String creatorName) {
		Optional<House> optHouse = houseRepository.findById(houseDto.getId());
//		if (!optHouse.isPresent() && !optHouse.get().createNewGroceryList(creatorName))
//		{
//			return null;
//		}
//
//		return houseRepository.save(optHouse.get());
		House house = optHouse.get();
		GroceryList groceryList = new GroceryList(house,creatorName);
		groceryListService.saveGroceryList(groceryList);
		
		return house;
	}
	
	@Override
	public Set<User> getUsersForHouse(HouseDto houseDto)
	{
		Optional<House> house = houseRepository.findByHouseName(houseDto.getHouseName());
		return (house.isPresent()?house.get().getUsers():null);
	}
	
	
	@Override
	public List<String> getAllGroceryListNamesForHouse(HouseDto houseDto)
	{
		Optional<House> optHouse = houseRepository.findByHouseName(houseDto.getHouseName());
		if(!optHouse.isPresent())
			return null;
		House house = optHouse.get();
		List<String> nameList = new ArrayList<>();
		for (GroceryList groceryList:house.getGroceryList())
		{
			nameList.add(groceryList.getDt_created().toString());
		}
		return nameList;
	}
	
	@Override
	public Boolean removeGroceryListForHouseByName(HouseDto houseDto,Long listId)
	{
		Optional<House> optHouse = houseRepository.findByHouseName(houseDto.getHouseName());
		if(!optHouse.isPresent())
			return false;
		House house = optHouse.get();
		List<GroceryList> groceryLists =  house.getGroceryList();
		int origSize = groceryLists.size();
		for (GroceryList gList : groceryLists)
		{
			if (listId == gList.getId())
			{
				groceryListService.removeGroceryList(gList);
				break;
			}
		}
		
		groceryLists =  house.getGroceryList();
		return groceryLists.size()+1 == origSize;
	}
	
	@Override
	public House getHouseForUser(HttpServletRequest request) {
		String userName = httpServletRequestService.getUserNameFromRequest(request);
		User user = userRepository.findByUsername(userName);
		return houseRepository.findById(user.getHouse().getId()).get();
	}
	
	@Override
	public House getHouseForUser(String userName) {
		User user = userRepository.findByUsername(userName);
		return houseRepository.findById(user.getHouse().getId()).get();
	}
	
	
	//TODO generate real saving of password
	@Override
	public boolean validatePassword(House house, String password) {
		String housePassword = house.getHousePassword();
		return housePassword.equals(password);
	}
	
	@Override
	public House addUserToHouse(House house,User joiningUser) {
		 return (house.addUser(joiningUser) ? houseRepository.save(house):null) ;
	
	}
	
}
