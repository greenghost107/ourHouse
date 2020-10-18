/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.service.impl;

import com.greenghost107.ourHouse.config.JwtTokenUtil;
import com.greenghost107.ourHouse.dto.GroceryListDto;
import com.greenghost107.ourHouse.model.GroceryList;
import com.greenghost107.ourHouse.model.House;
import com.greenghost107.ourHouse.model.User;
import com.greenghost107.ourHouse.repository.HouseRepository;
import com.greenghost107.ourHouse.repository.UserRepository;
import com.greenghost107.ourHouse.service.GroceryListService;
import com.greenghost107.ourHouse.service.HouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HouseServiceImpl implements HouseService {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	private HouseRepository houseRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private GroceryListService groceryListService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	

	
	@Override
	public House addHouse(House house) {
		return houseRepository.save(house);
	}
	
	
	//TODO generate real saving of password
	@Override
	public boolean validatePassword(House house, String password) {
		String housePassword = house.getHousePassword();
		return housePassword.equals(password);
	}
	
	@Override
	public User addUserToHouse(House house,User joiningUser) {
		joiningUser.setHouse(house);
		return userRepository.save(joiningUser);
		
	}
	
	@Override
	public Optional<House> findByHouseName(String houseName) {
		return houseRepository.findByHouseName(houseName);
		
	}

	@Override
	public House getHouseForUser(String token) {
		String userName = jwtTokenUtil.getUserNameFromBearerToken(token);
		User user = userRepository.findByUsername(userName);
		Optional<House> optHouse = houseRepository.findById(user.getHouse().getId());
		if(!optHouse.isPresent())
		{
			LOGGER.info("No House was found for user " + userName);
			return null;
		}
		return optHouse.get();

	}

	@Override
	public GroceryList createNewGroceryList(String token, Long houseId,GroceryListDto groceryListDto) {
		String userName = jwtTokenUtil.getUserNameFromBearerToken(token);

		Optional<House> optHouse = houseRepository.findById(houseId);

		if (!optHouse.isPresent())
		{
			return null;
		}

		//create the list and save it
		return groceryListService.createNewGroceryListForHouse(optHouse.get(),userName,groceryListDto.getGroceryListName());
	}

}
