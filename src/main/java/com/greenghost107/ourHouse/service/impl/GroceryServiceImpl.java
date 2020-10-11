/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.service.impl;

import com.greenghost107.ourHouse.model.Grocery;
import com.greenghost107.ourHouse.model.GroceryList;
import com.greenghost107.ourHouse.repository.GroceryRepository;
import com.greenghost107.ourHouse.service.GroceryService;
import com.greenghost107.ourHouse.service.HttpServletRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class GroceryServiceImpl implements GroceryService {
	
	@Autowired
	private GroceryRepository groceryRepository;
	
	@Autowired
	private HttpServletRequestService httpServletRequestService;
	
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
	public List<Grocery> saveGroceries(HttpServletRequest request,GroceryList groceryList) {
	List<Grocery> groceries = httpServletRequestService.getArrayOfGroceries(request);
	groceries.forEach(g->g.setGroceryList(groceryList));
	return groceryRepository.saveAll(groceries);
	}
}
