/*
 * Created by greenghost107 on Oct/2020
 */
package com.greenghost107.ourHouse.repository.security;

import com.greenghost107.ourHouse.model.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Role findByName(String name);
	
	@Override
	void delete(Role role);
}
