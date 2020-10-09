/*
 * Created by greenghost107 on Oct/2020
 */
package com.greenghost107.ourHouse.service;

import com.greenghost107.ourHouse.model.House;
import com.greenghost107.ourHouse.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface HttpServletRequestService {
	
	String getUserNameFromRequest(HttpServletRequest request);
	
	Optional<House> getHouseFromJson(HttpServletRequest request);
	
	House createNewHouseFromJson(HttpServletRequest request, User user);
}
