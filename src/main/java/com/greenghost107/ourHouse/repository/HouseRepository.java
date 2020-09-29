/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.repository;

import com.greenghost107.ourHouse.dto.HouseDto;
import com.greenghost107.ourHouse.model.GroceryList;
import com.greenghost107.ourHouse.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
	
//	@Query(value = "SELECT * FROM HOUSE house WHERE house.house_name = ?1",nativeQuery = true)
	House findByHouseName(String houseName);
	
	@Override
	void delete(House user);
}
