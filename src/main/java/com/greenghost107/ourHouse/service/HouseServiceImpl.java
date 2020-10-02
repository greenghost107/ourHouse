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

import java.util.ArrayList;
import java.util.List;
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
//		House house = houseRepository.findByHouseName(houseDto.getHouseName());
//		if (!house.createNewGroceryList(userDto.getUserName()))
//		{
//			return null;
//		}
//		houseRepository.save(house);
//		return house;
		return createNewGroceryList(userDto.getUserName(),houseDto.getHouseName());
	}
	@Override
	public House createNewGroceryList(String userName, String houseName) {
		House house = houseRepository.findByHouseName(houseName);
		if (!house.createNewGroceryList(userName))
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
	public List<String> getAllGroceryListNamesForHouse(HouseDto houseDto)
	{
//		House house = houseRepository.findByHouseName(houseDto.getHouseName());
//		List<String> nameList = new ArrayList<>();
//		for (GroceryList groceryList:house.getGroceryList())
//		{
//			nameList.add(groceryList.getDt_created().toString());
//		}
//		return nameList;
		return getAllGroceryListNamesForHouse(houseDto.getHouseName());
	}
	
	@Override
	public List<String> getAllGroceryListNamesForHouse(String houseName) {
		House house = houseRepository.findByHouseName(houseName);
		List<String> nameList = new ArrayList<>();
		for (GroceryList groceryList:house.getGroceryList())
		{
			nameList.add(groceryList.getDt_created().toString());
		}
		return nameList;
	}
	
	//TODO check that logics works
	@Override
	public Boolean removeGroceryListForHouseByName(HouseDto houseDto,Long listId)
	{
		House house = houseRepository.findByHouseName(houseDto.getHouseName());
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
	public House findByHouseName(String username) {
		return houseRepository.findByHouseName(username);
	}
	
	
	
}
