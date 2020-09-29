/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.repository;

import com.greenghost107.ourHouse.model.Grocery;
import com.greenghost107.ourHouse.model.GroceryList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceryRepository extends JpaRepository<Grocery, Long> {
	Grocery findGroceryByName(String groceryName);
}
