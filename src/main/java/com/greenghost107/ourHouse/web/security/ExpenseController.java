package com.greenghost107.ourHouse.web.security;

import com.greenghost107.ourHouse.dto.ExpenseDto;
import com.greenghost107.ourHouse.dto.GroceryListDto;
import com.greenghost107.ourHouse.exceptions.SpringException;
import com.greenghost107.ourHouse.service.ExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private ExpenseService expenseService;

    @RequestMapping(value = "/addExpense/{houseId}", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addExpense(@RequestHeader("Authorization") String token, @PathVariable( "houseId" ) Long houseId, @RequestBody ExpenseDto expenseDto) {
        LOGGER.debug("addExpense" );
        return Optional.ofNullable(expenseService.addExpense(token,houseId,expenseDto))
                .map(exp -> new ResponseEntity<>(exp, HttpStatus.OK))
                .orElseThrow(() -> new SpringException("Couldn't add expense"));
    }

    @RequestMapping(value = "/{houseId}", method = RequestMethod.GET)
    public ResponseEntity<?> getExpensesForHouse(@PathVariable( "houseId" ) Long houseId)
    {
        LOGGER.debug("getExpensesFor house " + houseId);
        return Optional.ofNullable(expenseService.getExpensesForHouse(houseId))
                .map(grocLst -> new ResponseEntity<>(grocLst, HttpStatus.OK))
                .orElseThrow(() -> new SpringException("Couldn't get expenses for house with id +" + houseId));

    }

}
