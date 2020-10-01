package com.greenghost107.ourHouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@EnableAutoConfiguration
//@ComponentScan(basePackages={"com.greenghost107.ourHouse"})
//@EnableJpaRepositories(basePackages="com.greenghost107.ourHouse.repository")
//@EnableTransactionManagement
//@EntityScan(basePackages="com.greenghost107.ourHouse.model")
public class OurHouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(OurHouseApplication.class, args);
	}

}
