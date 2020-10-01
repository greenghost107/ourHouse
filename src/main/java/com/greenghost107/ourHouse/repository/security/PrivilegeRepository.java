/*
 * Created by greenghost107 on Oct/2020
 */
package com.greenghost107.ourHouse.repository.security;

import com.greenghost107.ourHouse.model.security.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
	
	Privilege findByName(String name);
	

	
}