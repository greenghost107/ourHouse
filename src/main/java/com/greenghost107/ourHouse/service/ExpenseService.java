package com.greenghost107.ourHouse.service;

import com.greenghost107.ourHouse.dto.ExpenseDto;
import com.greenghost107.ourHouse.model.Expense;

import java.util.List;

public interface ExpenseService {

    Boolean addExpense(String token, Long houseId, ExpenseDto expenseDto);

    Expense addExpenseDoneShopping(String token, Long groceryListId, Double price);

    List<Expense> getExpensesForHouse(Long houseId);
}
