/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.service.impl;

import com.greenghost107.ourHouse.dto.HouseDto;
import com.greenghost107.ourHouse.dto.UserDto;
import com.greenghost107.ourHouse.model.GroceryList;
import com.greenghost107.ourHouse.model.House;
import com.greenghost107.ourHouse.model.User;
import com.greenghost107.ourHouse.repository.HouseRepository;
import com.greenghost107.ourHouse.repository.UserRepository;
import com.greenghost107.ourHouse.service.GroceryListService;
import com.greenghost107.ourHouse.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class HouseServiceImpl implements HouseService {
	@Autowired
	private HouseRepository houseRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private GroceryListService groceryListService;
	
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
//			house.getUsers().stream().forEach(u-> System.out.println(u.getusername()));
//			return houseRepository.save(house);
//		}
//		return null;
//
//
//	}
	
	@Override
	public House createNewGroceryList(UserDto userDto,HouseDto houseDto)
	{
		Optional<House> optHouse = houseRepository.findByHouseName(houseDto.getHouseName());
		
		if (!optHouse.isPresent() && !optHouse.get().createNewGroceryList(userDto.getusername()))
		{
			return null;
		}
		return houseRepository.save(optHouse.get());
		
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
	
	
	
}
