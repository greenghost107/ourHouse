/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.service;

import com.greenghost107.ourHouse.dto.HouseDto;
import com.greenghost107.ourHouse.dto.UserDto;
import com.greenghost107.ourHouse.model.GroceryList;
import com.greenghost107.ourHouse.model.House;
import com.greenghost107.ourHouse.model.User;
import com.greenghost107.ourHouse.repository.HouseRepository;
import com.greenghost107.ourHouse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.tree.Tree;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class HouseServiceImpl implements HouseService {
	@Autowired
	private HouseRepository houseRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public House addHouse(House house) {
		return houseRepository.save(house);
	}
	
//	@Override
//	public House addUserToHouse(HouseDto houseDto,String userName)
//	{
//		House house = houseRepository.findByHouseName(houseDto.getHouseName());
//		User user = userRepository.findByUsername(userName);
//		if (house.addUser(user))
//		{
//			house.getUsers().stream().forEach(u-> System.out.println(u.getUsername()));
//			return houseRepository.save(house);
//		}
//		return null;
//
//
//	}
	
	@Override
	public House createNewGroceryList(UserDto userDto,HouseDto houseDto)
	{
		House house = houseRepository.findByHouseName(houseDto.getHouseName());
		if (!house.createNewGroceryList(userDto.getUserName()))
		{
			return null;
		}
		houseRepository.save(house);
		return house;
	}
	
	@Override
	public Set<User> getUsersForHouse(HouseDto houseDto)
	{
		House house = houseRepository.findByHouseName(houseDto.getHouseName());
		return house.getUsers();
	}
	
	@Override
	public GroceryList getLastGroceryListForHouse(HouseDto houseDto,UserDto userDto)
	{
//		House house = houseRepository.findByHouseName(houseDto.getHouseName());
//		Set<GroceryList> groceryLists = house.getGroceryList();
//		return (groceryLists.isEmpty()?null:(GroceryList)groceryLists.toArray()[groceryLists.size()-1]);
		House house = houseRepository.findByHouseName(houseDto.getHouseName());
		
		return house.getLastGroceryList(userDto.getUserName());
	}
	
	
	
}
