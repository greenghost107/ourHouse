package com.greenghost107.ourHouse.service.impl;

import com.greenghost107.ourHouse.config.JwtTokenUtil;
import com.greenghost107.ourHouse.dto.ExpenseDto;
import com.greenghost107.ourHouse.model.Expense;
import com.greenghost107.ourHouse.model.GroceryList;
import com.greenghost107.ourHouse.model.House;
import com.greenghost107.ourHouse.repository.ExpenseRepository;
import com.greenghost107.ourHouse.repository.GroceryListRepository;
import com.greenghost107.ourHouse.repository.HouseRepository;
import com.greenghost107.ourHouse.service.ExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private GroceryListRepository groceryListRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public Boolean addExpense(String token, Long houseId, ExpenseDto expenseDto) {
        String userName = jwtTokenUtil.getUserNameFromBearerToken(token);

        Optional<House> optHouse = houseRepository.findById(houseId);

        if (!optHouse.isPresent())
        {
            LOGGER.error("Couldn't find house with id " + houseId);
            return false;
        }
        Expense expense = expenseRepository.save(new Expense(expenseDto.getExpenseName(),expenseDto.getPrice(),expenseDto.getNotes(),userName,optHouse.get()));
        return (expense!=null?true:false);

    }

    @Override
    public Expense addExpenseDoneShopping(String token, Long groceryListId, Double price) {
    String userName = jwtTokenUtil.getUserNameFromBearerToken(token);
    Optional<GroceryList> optGroceryList = groceryListRepository.findById(groceryListId);
    if (!optGroceryList.isPresent())
    {
        LOGGER.error("Couldn't find groceryList with id " + groceryListId);
        return null;
    }
    GroceryList groceryList = optGroceryList.get();
    House house = groceryList.getHouse();
    return expenseRepository.save(new Expense(groceryList.getGroceryListName(),price,"",userName,house));
    }

    @Override
    public List<Expense> getExpensesForHouse(Long houseId) {
        return expenseRepository.findExpenseByHouseId(houseId);
    }
}
