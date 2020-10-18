package com.greenghost107.ourHouse.service.impl;

import com.greenghost107.ourHouse.model.House;
import com.greenghost107.ourHouse.repository.HouseRepository;
import com.greenghost107.ourHouse.service.HouseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class HouseServiceImplTest {

    @Mock
    HouseRepository houseRepository;

    @Autowired
    HouseService houseService;

    House house;
    @BeforeEach
    void setUp() {
        house = new House("test1","test1");
        when(houseRepository.findByHouseName(house.getHouseName())).thenReturn(Optional.of(house));
    }

    @Test
    void findByHouseName() {
        assertEquals(houseService.findByHouseName("test1"),Optional.of(this.house));
    }
}