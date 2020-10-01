/*
 * Created by greenghost107 on Oct/2020
 */
package com.greenghost107.ourHouse.repository.security;

import com.greenghost107.ourHouse.model.User;
import com.greenghost107.ourHouse.model.security.Privilege;
import com.greenghost107.ourHouse.model.security.Role;
import com.greenghost107.ourHouse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class InitialDataLoader implements
		ApplicationListener<ContextRefreshedEvent> {
	
	boolean alreadySetup = false;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PrivilegeRepository privilegeRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		if (alreadySetup)
			return;
		Privilege readPrivilege
				= createPrivilegeIfNotFound("READ_PRIVILEGE");
		Privilege writePrivilege
				= createPrivilegeIfNotFound("WRITE_PRIVILEGE");

//        List<Privilege> adminPrivileges = Arrays.asList(
//          readPrivilege, writePrivilege);
		Set<Privilege> adminPrivileges = Stream.of(
				readPrivilege, writePrivilege).collect(Collectors.toSet());
		createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
//        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));
		createRoleIfNotFound("ROLE_USER", Stream.of(readPrivilege).collect(Collectors.toSet()));
		
		Role adminRole = roleRepository.findByName("ROLE_ADMIN");
		User user = new User();
		user.setUsername("admin");
		user.setPassword(bCryptPasswordEncoder.encode("admin"));
		
		user.setRoles(Stream.of(adminRole).collect(Collectors.toSet()));
		//sanityCheck
		if(userRepository.findByUsername("admin")!=null)
			return;
		userRepository.save(user);
		
		alreadySetup = true;
	}
	
	@Transactional
	private Privilege createPrivilegeIfNotFound(String name) {
		
		Privilege privilege = privilegeRepository.findByName(name);
		if (privilege == null) {
			privilege = new Privilege(name);
			privilegeRepository.save(privilege);
		}
		return privilege;
	}
	
	@Transactional
	private Role createRoleIfNotFound(
			String name, Set<Privilege> privileges) {
		
		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role(name);
			role.setPrivileges(privileges);
			roleRepository.save(role);
		}
		return role;
	}
}
