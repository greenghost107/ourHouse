/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.service.impl;

import com.greenghost107.ourHouse.dto.HouseDto;
import com.greenghost107.ourHouse.dto.UserDto;
import com.greenghost107.ourHouse.model.House;
import com.greenghost107.ourHouse.model.User;
import com.greenghost107.ourHouse.repository.HouseRepository;
import com.greenghost107.ourHouse.repository.UserRepository;
import com.greenghost107.ourHouse.service.HouseService;
import com.greenghost107.ourHouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private HouseRepository houseRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HouseService houseService;
	
	@Override
	public User saveUser(UserDto userDto) {
		return userRepository.save(new User(userDto.getUserName()));
	}
	
	@Override
	public User findByUserName(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	public List<User> findAllUsers()
	{
	return userRepository.findAll();
	}
	
	@Override
	public User joinUserToHouse(UserDto userDto,HouseDto houseDto)
	{
		User user = userRepository.findByUsername(userDto.getUserName());
		House house = houseRepository.findByHouseName(houseDto.getHouseName());
		if (house == null)
		{
			house = houseService.addHouse(new House(houseDto.getHouseName(),user));
		}
		user.setHouse(house);
		return userRepository.save(user);
	}
	
	
	
}
