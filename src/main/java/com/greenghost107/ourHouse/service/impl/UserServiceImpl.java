/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.service.impl;

import com.greenghost107.ourHouse.config.JwtTokenUtil;
import com.greenghost107.ourHouse.dto.HouseDto;
import com.greenghost107.ourHouse.model.House;
import com.greenghost107.ourHouse.model.User;
import com.greenghost107.ourHouse.repository.HouseRepository;
import com.greenghost107.ourHouse.repository.UserRepository;
import com.greenghost107.ourHouse.service.HouseService;
import com.greenghost107.ourHouse.service.UserService;
import javafx.beans.binding.IntegerBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private HouseRepository houseRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HouseService houseService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;


	@Override
	public User findByUserName(String userName) {
		return userRepository.findByUsername(userName);
	}

	@Override
	public House getHouseForUser() {
		return null;
	}

	@Override
	public House createHouseForUser(String userName, String houseName, String housePassword) {
		User user = userRepository.findByUsername(userName);
		//TODO validate houseName
		//TODO handle password
		House house = houseService.addHouse(new House(houseName,housePassword,user));
		user.setHouse(house);
		userRepository.save(user);
		return house;
	}
	
	@Override
	public House joinHouse(String joiningUserName, String houseName, String password) {
		User joiningUser = userRepository.findByUsername(joiningUserName);
		Optional<House> optHouse = houseRepository.findByHouseName(houseName);
		if(!optHouse.isPresent())
		{
		LOGGER.error("no house with the name "+ houseName);
		return null;
		}
		House house = optHouse.get();
		if(houseService.validatePassword(house,password))
		{
			LOGGER.info("user " + joiningUserName + " joined house " + houseName);
			if (houseService.addUserToHouse(house,joiningUser)==null)
			{
				LOGGER.error("Failed to save in db");
				return null;
			}
			return house;
		}
		else
		{
			LOGGER.error("couldn't join user " + joiningUserName + " to house " + houseName + " password incorrect");
		}
		return null;
	}

	@Override
	public House createHouseForUser(String token, HouseDto houseDto) {
		String userName = jwtTokenUtil.getUserNameFromBearerToken(token);
		User user = userRepository.findByUsername(userName);
		//TODO validate houseName
		//TODO handle password
		Optional<House> optHouse = houseService.findByHouseName(houseDto.getHouseName());
		if(optHouse.isPresent())
		{
			LOGGER.error("House with name " + houseDto.getHouseName() + " already exists");
			return null;
		}
		House house = houseService.addHouse(new House(houseDto.getHouseName(),houseDto.getHousePassword()));
		user.setHouse(house);
		userRepository.save(user);
		return house;


	}

	@Override
	public User findUserFromToken(String token) {
		String userName = jwtTokenUtil.getUserNameFromBearerToken(token);
		return userRepository.findByUsername(userName);
	}

	@Override
	public House joinHouse(String token, HouseDto houseDto) {
		String userName = jwtTokenUtil.getUserNameFromBearerToken(token);
		User joiningUser = userRepository.findByUsername(userName);
		Optional<House> optHouse = houseService.findByHouseName(houseDto.getHouseName());

		if(!optHouse.isPresent())
		{
			LOGGER.error("no house with the name " + houseDto.getHouseName());
			return null;
		}
		House house = optHouse.get();
		if(houseService.validatePassword(house,houseDto.getHousePassword()))
		{
			LOGGER.info("user " + userName + " joined house " + houseDto.getHouseName());
			if (houseService.addUserToHouse(house,joiningUser)==null)
			{
				LOGGER.error("Failed to save in db");
				return null;
			}
			return house;
		}
		else
		{
			LOGGER.error("couldn't join user " + userName + " to house " + houseDto.getHouseName() + " password incorrect");
		}
		return null;
	}

}
