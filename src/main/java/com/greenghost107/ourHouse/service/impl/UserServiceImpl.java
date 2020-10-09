/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.service.impl;

import com.greenghost107.ourHouse.config.JwtTokenUtil;
import com.greenghost107.ourHouse.dto.HouseDto;
import com.greenghost107.ourHouse.dto.UserDto;
import com.greenghost107.ourHouse.model.House;
import com.greenghost107.ourHouse.model.User;
import com.greenghost107.ourHouse.repository.HouseRepository;
import com.greenghost107.ourHouse.repository.UserRepository;
import com.greenghost107.ourHouse.service.HouseService;
import com.greenghost107.ourHouse.service.HttpServletRequestService;
import com.greenghost107.ourHouse.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

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
	private HttpServletRequestService httpServletRequestService;
	
	@Override
	public User saveUser(UserDto userDto) {
		return userRepository.save(new User(userDto.getusername()));
	}
	
	@Override
	public User findByUserName(HttpServletRequest request) {
		String userName = httpServletRequestService.getUserNameFromRequest(request);
		return userRepository.findByUsername(userName);
	}
	
	
	@Override
	public List<User> findAllUsers()
	{
	return userRepository.findAll();
	}
	
	@Override
	public User joinUserToHouse(UserDto userDto,HouseDto houseDto)
	{
		User user = userRepository.findByUsername(userDto.getusername());
		Optional<House> opthouse = houseRepository.findByHouseName(houseDto.getHouseName());
		House house = (opthouse.isPresent()?opthouse.get():houseService.addHouse(new House(houseDto.getHouseName(), "",user)));

		user.setHouse(house);
		return userRepository.save(user);
	}
	
	@Override
	public House createHouseForUser(HttpServletRequest request) {
		String userName = httpServletRequestService.getUserNameFromRequest(request);
		User user = userRepository.findByUsername(userName);
		//TODO validate houseName
		//TODO handle password
		House house = httpServletRequestService.createNewHouseFromJson(request,user);
		if(house==null)
		{
			LOGGER.error("Couldn't create object with the credentials");
		}
		house = houseService.addHouse(house);
		user.setHouse(house);
		userRepository.save(user);
		return house;
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
			return houseService.addUserToHouse(house,joiningUser);
		}
		else
		{
			LOGGER.error("couldn't join user " + joiningUserName + " to house " + houseName + " password incorrect");
		}
		return null;
	}
	
	@Override
	public House joinHouse(HttpServletRequest request) {
		String userName = httpServletRequestService.getUserNameFromRequest(request);
		User joiningUser = userRepository.findByUsername(userName);
		Optional<House> optHouse = httpServletRequestService.getHouseFromJson(request);
		House inputHouse = httpServletRequestService.createNewHouseFromJson(request,null);
		
		if(!optHouse.isPresent())
		{
			LOGGER.error("no house with the name " + inputHouse.getHouseName());
			return null;
		}
		House house = optHouse.get();
		if(houseService.validatePassword(house,inputHouse.getHousePassword()))
		{
			LOGGER.info("user " + userName + " joined house " + inputHouse.getHouseName());
			return houseService.addUserToHouse(house,joiningUser);
		}
		else
		{
			LOGGER.error("couldn't join user " + userName + " to house " + inputHouse.getHouseName() + " password incorrect");
		}
		return null;
		
	}
	
}
