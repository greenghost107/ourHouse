/*
 * Created by greenghost107 on Sep/2020
 */
package com.greenghost107.ourHouse.repository;

import com.greenghost107.ourHouse.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
	
	@Override
	void delete(User user);
}
