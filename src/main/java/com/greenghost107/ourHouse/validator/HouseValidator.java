/*
 * Created by greenghost107 on Oct/2020
 */
package com.greenghost107.ourHouse.validator;

import com.greenghost107.ourHouse.model.House;
import com.greenghost107.ourHouse.model.User;
import com.greenghost107.ourHouse.service.HouseService;
import com.greenghost107.ourHouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class HouseValidator implements Validator {
	@Autowired
	private HouseService houseService;
	
	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}
	
	@Override
	public void validate(Object o, Errors errors) {
		House house = (House) o;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "houseName", "NotEmpty");
		if (house.getHouseName().length() < 6 || house.getHouseName().length() > 32) {
			errors.rejectValue("houseName", "Size.house.houseName");
		}
//		if (houseService.findByHouseName(house.getHouseName()) != null) {
//			errors.rejectValue("houseName", "Duplicate.house.houseName");
//		}
	}
	
	
}