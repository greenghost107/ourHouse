/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.repository;

import com.greenghost107.ourHouse.model.Grocery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroceryRepository extends JpaRepository<Grocery, Long> {

		List<Grocery> findAllGroceriesByGroceryListId(Long groceryListId);
	@Modifying(clearAutomatically = true)
	@Query(value = "delete from GROCERY where grocery_list_id = ?1",nativeQuery = true)
		void deleteAllByGroceryListId(Long groceryListId);
}
