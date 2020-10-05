/*
 * Created by greenghost107 on Oct/2020
 */
package com.greenghost107.ourHouse.service;

import com.greenghost107.ourHouse.dto.UserDto;

import com.greenghost107.ourHouse.model.security.JwtRequest;
import com.greenghost107.ourHouse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		if ("javainuse".equals(username)) {
//			return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
//					new ArrayList<>());
//		} else {
//			throw new UsernameNotFoundException("User not found with username: " + username);
//		}
		com.greenghost107.ourHouse.model.User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}
	
	public com.greenghost107.ourHouse.model.User save(JwtRequest jwtRequest) {
		com.greenghost107.ourHouse.model.User newUser = new com.greenghost107.ourHouse.model.User();
		newUser.setUsername(jwtRequest.getUsername());
		newUser.setPassword(bcryptEncoder.encode(jwtRequest.getPassword()));
		return userRepository.save(newUser);
	}
}
