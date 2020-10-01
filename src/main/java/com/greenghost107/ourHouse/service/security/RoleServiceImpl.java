/*
 * Created by greenghost107 on Oct/2020
 */
package com.greenghost107.ourHouse.service.security;

import java.util.Set;

import com.greenghost107.ourHouse.model.User;
import com.greenghost107.ourHouse.model.security.Role;
import com.greenghost107.ourHouse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Set<Role> findRoleByUserName(String login) {
		User user =userRepository.findByUsername(login);
		Set<Role> roleList = user.getRoles();
		return roleList;
	}
	
	
}