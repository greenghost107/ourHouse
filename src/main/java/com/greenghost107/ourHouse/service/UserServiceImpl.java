/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.service;

import com.greenghost107.ourHouse.dto.HouseDto;
import com.greenghost107.ourHouse.dto.UserDto;
import com.greenghost107.ourHouse.model.House;
import com.greenghost107.ourHouse.model.User;
import com.greenghost107.ourHouse.repository.HouseRepository;
import com.greenghost107.ourHouse.repository.UserRepository;
import com.greenghost107.ourHouse.repository.security.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private HouseRepository houseRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HouseService houseService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<>(roleRepository.findAll()));
		user.setRoles(Stream.of(roleRepository.findByName("ROLE_USER")).collect(Collectors.toSet()));
		userRepository.save(user);
	}
	
	@Override
	public boolean isValidUser(User user) {
		User isValidUser = userRepository.findByUsername(user.getUsername());
		boolean bool1 = isValidUser!=null;
		boolean bool2 = bCryptPasswordEncoder.matches(user.getPassword(),isValidUser.getPassword());
		return bool1 & bool2;
		
	}
	
	@Override
	public User saveUser(UserDto userDto) {
		return userRepository.save(new User(userDto.getUserName()));
	}
	
	@Override
	public User findByUsername(String username) {
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
