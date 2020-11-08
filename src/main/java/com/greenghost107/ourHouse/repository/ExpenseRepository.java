package com.greenghost107.ourHouse.repository;

import com.greenghost107.ourHouse.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    @Modifying(clearAutomatically = true)
    @Query(value = "select * from expense where house_id = ?1",nativeQuery = true)
    List<Expense> findExpenseByHouseId(Long houseId);
}
