/*
 * Created by greenghost107 on Oct/2020
 */
package com.greenghost107.ourHouse.service.security;

import com.greenghost107.ourHouse.model.security.Role;

import java.util.Set;




public interface RoleService {
	
	Set<Role> findRoleByUserName(String login);
}
