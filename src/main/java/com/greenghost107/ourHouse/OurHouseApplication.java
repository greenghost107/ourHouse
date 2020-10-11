package com.greenghost107.ourHouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class OurHouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(OurHouseApplication.class, args);
	}

}
