/*
 * Created by greenghost107 on Oct/2020
 */
package com.greenghost107.ourHouse.service.security;

import java.util.HashSet;
import java.util.Set;

import com.greenghost107.ourHouse.model.User;
import com.greenghost107.ourHouse.model.security.Privilege;
import com.greenghost107.ourHouse.model.security.Role;
import com.greenghost107.ourHouse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) {
		User user = userRepository.findByUsername(username);
		if (user == null) throw new UsernameNotFoundException(username);
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (Role role : user.getRoles()){
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
	}
	
	
	private final Set<? extends GrantedAuthority> getAuthorities(final Set<Role> roles) {
		return getGrantedAuthorities(getPrivileges(roles));
	}
	
	private final Set<String> getPrivileges(final Set<Role> roles) {
		final Set<String> privileges = new HashSet<String>();
		final Set<Privilege> collection = new HashSet<Privilege>();
		for (final Role role : roles) {
			collection.addAll(role.getPrivileges());
		}
		for (final Privilege item : collection) {
			privileges.add(item.getName());
		}
		
		return privileges;
	}
	
	private final Set<GrantedAuthority> getGrantedAuthorities(final Set<String> privileges) {
		final Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for (final String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}
	
}
