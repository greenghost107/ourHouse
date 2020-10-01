/*
 * Created by greenghost107 on Oct/2020
 */
package com.greenghost107.ourHouse.web;

import com.greenghost107.ourHouse.dto.UserDto;
import com.greenghost107.ourHouse.model.User;
import com.greenghost107.ourHouse.service.UserService;
import com.greenghost107.ourHouse.service.security.SecurityService;
import com.greenghost107.ourHouse.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = {"/"},method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = {"/"})
	public String index(@ModelAttribute("userForm") User user, BindingResult bindingResult) {
		if (userService.isValidUser(user)) {
			securityService.autoLogin(user.getUsername(), user.getPassword());
			return "user/index";
		}
		return "index";
	}
	
	@RequestMapping(value = {"/registration"},method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("userForm", new User());
		
		return "registration";
	}
	@RequestMapping(value = {"/registration"},method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
		
		userValidator.validate(userForm, bindingResult);
		
		if (bindingResult.hasErrors()) {
			
			return "registration";
		}
		
		userService.save(userForm);
		
		securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());
		
		return "user/index";
	}
	@RequestMapping(value = {"/user/index"},method = RequestMethod.GET)
	public ModelAndView userIndex(){
		ModelAndView model = new ModelAndView("/user/index");
		model.addObject("temp","SomeMessage");
		return model;
	}
	
	@RequestMapping("/loginError")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "index";
	}
	
//		@GetMapping("/greeting")
//	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
//		model.addAttribute("name", name);
//		return "greeting";
//	}
//
//	@RequestMapping(value = "/signup",method = RequestMethod.GET)
//	public String showSignUpForm(UserDto userDto) {
//		return "add-user";
//	}
//	@RequestMapping(value = "/adduser",method = RequestMethod.POST)
//	public String addUser(UserDto userDto, BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			return "add-user";
//		}
//		userService.saveUser(userDto);
//		model.addAttribute("users", userService.findAllUsers());
////		userRepository.save(user);
////		model.addAttribute("users", userRepository.findAll());
//
//		return "redirect:/index";
//	}
//
//
//	@GetMapping("/hello")
//	public String hello(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
//		model.addAttribute("name", name);
//		return "hello";
//	}
	
	
}
