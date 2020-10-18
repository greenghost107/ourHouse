/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.service.impl;

import com.greenghost107.ourHouse.dto.GroceryDto;
import com.greenghost107.ourHouse.model.Grocery;
import com.greenghost107.ourHouse.model.GroceryList;
import com.greenghost107.ourHouse.repository.GroceryRepository;
import com.greenghost107.ourHouse.service.GroceryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GroceryServiceImpl implements GroceryService {
	
	@Autowired
	private GroceryRepository groceryRepository;

	@Transactional
	@Override
	public void removeGroceriesByGroceryListId(Long groceryListId) {
		groceryRepository.deleteAllByGroceryListId(groceryListId);
	}
	
	@Override
	public Grocery addGrocery(String name, double quantity, GroceryList groceryList) {
		return groceryRepository.save(new Grocery(name, quantity, groceryList));
	}
	@Transactional
	@Override
	public void deleteGrocery(Grocery grocery) {
		groceryRepository.delete(grocery);

	}
	
	@Override
	public List<Grocery> getByGroceryListId(Long id) {
		return groceryRepository.findAllGroceriesByGroceryListId(id);
		
	}

	@Override
	public List<Grocery> saveGroceries(List<GroceryDto> groceries, GroceryList groceryList) {
		List<Grocery> savedGroceries = groceries.stream().map(gdt->new Grocery(gdt.getName(),gdt.getQuantity(),groceryList)).collect(Collectors.toList());

//		groceries.forEach(g->g.setGroceryList(groceryList));
		return groceryRepository.saveAll(savedGroceries);
	}

//	@Override
//	public List<Grocery> markGroceries(List<Grocery> groceries, Long groceryListId) {
//		List<Grocery> savedGroceries = groceryRepository.findAllGroceriesByGroceryListId(groceryListId);
//		List<String> names = getGroceryNames(groceries);
//		savedGroceries = savedGroceries.stream().filter(g->names.contains(g.getName())).collect(Collectors.toList());
//		savedGroceries.stream().forEach(g->g.setMarked(true));
//		return groceryRepository.saveAll(savedGroceries);
//	}
//
//	private List<String> getGroceryNames(List<Grocery> groceries)
//	{
//		return groceries.stream().map(g->g.getName()).collect(Collectors.toList());
//	}

	@Override
	public List<Grocery> markGroceries(List<Grocery> groceries, Long groceryListId) {
		List<Grocery> savedGroceries = groceryRepository.findAllGroceriesByGroceryListId(groceryListId);
		Map<String,Boolean> nameMap = getGroceryNameMarkingMap(groceries);
		savedGroceries = savedGroceries.stream().filter(g->nameMap.containsKey(g.getName())).collect(Collectors.toList());
		savedGroceries.stream().forEach(g->g.setMarked((nameMap.get(g.getName()))));
		
		return groceryRepository.saveAll(savedGroceries);
	}
	//get the grocery name as key and isMarked as value
	private Map<String,Boolean> getGroceryNameMarkingMap(List<Grocery> groceries)
	{
		Map<String,Boolean> groceryMap = new HashMap<>();
		groceries.stream().forEach(g->groceryMap.put(g.getName(),g.getMarked()));
		return groceryMap;
	}

}
