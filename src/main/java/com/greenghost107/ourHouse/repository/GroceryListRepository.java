/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.repository;

import com.greenghost107.ourHouse.model.GroceryList;
import com.greenghost107.ourHouse.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceryListRepository  extends JpaRepository<GroceryList, Long> {
	GroceryList findGroceryListByHouse(House house);
	
	void deleteById(Long id);
	

}
