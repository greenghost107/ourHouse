/*
 * Created by greenghost107 on Oct/2020
 */
package com.greenghost107.ourHouse.web;

import com.greenghost107.ourHouse.dto.UserDto;
import com.greenghost107.ourHouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = {"/","/index"},method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("users", userService.findAllUsers());
		return "index";
	}
	
	@RequestMapping(value = "/signup",method = RequestMethod.GET)
	public String showSignUpForm(UserDto userDto) {
		return "add-user";
	}
	@RequestMapping(value = "/adduser",method = RequestMethod.POST)
	public String addUser(UserDto userDto, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-user";
		}
		userService.saveUser(userDto);
		model.addAttribute("users", userService.findAllUsers());
//		userRepository.save(user);
//		model.addAttribute("users", userRepository.findAll());
		
		return "redirect:/index";
	}
	
	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
	
}
