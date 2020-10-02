/*
 * Created by greenghost107 on Oct/2020
 */
package com.greenghost107.ourHouse.web;

import com.greenghost107.ourHouse.dto.UserDto;
import com.greenghost107.ourHouse.model.House;
import com.greenghost107.ourHouse.model.User;
import com.greenghost107.ourHouse.service.GroceryListService;
import com.greenghost107.ourHouse.service.HouseService;
import com.greenghost107.ourHouse.service.UserService;
import com.greenghost107.ourHouse.service.security.SecurityService;
import com.greenghost107.ourHouse.validator.HouseValidator;
import com.greenghost107.ourHouse.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private HouseValidator houseValidator;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HouseService houseService;
	
	@Autowired
	private GroceryListService groceryListService;
	
	@RequestMapping(value = {"/"},method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = {"/"})
	public String index(@ModelAttribute("userForm") User user, BindingResult bindingResult) {
		if (userService.isValidUser(user)) {
			securityService.autoLogin(user.getUsername(), user.getPassword());
			return "redirect:/user/userHomeScreen";
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
		
		return "/user/userHomeScreen";
	}

	@RequestMapping(value = {"/user/userHomeScreen"},method = RequestMethod.GET)
	public String userHomeScreen(Model model){
		
		String userName = securityService.findLoggedInUsername();
		House house = userService.findHouseForUser(userName);
		model.addAttribute("house", house);
		model.addAttribute("groceryLists", houseService.getAllGroceryListNamesForHouse(house.getHouseName()));
		return "/user/userHomeScreen";
	}
	@RequestMapping(value = {"/user/addHouse"},method = RequestMethod.GET)
	public String userAddHouse(Model model) {
//		model.addAttribute("house", userService.findHouseForUser(securityService.findLoggedInUsername()));
//
//		return "/user/userHomeScreen";
		model.addAttribute("house", new House());
		
		return "/user/addHouse";
	}
	
	@RequestMapping(value = {"/user/addHouse"},method = RequestMethod.POST)
	public String userAddHouse(@ModelAttribute("house") House house, BindingResult bindingResult) {
		
		houseValidator.validate(house, bindingResult);
		
		if (bindingResult.hasErrors()) {
			LOGGER.error("Add house for " + house.getHouseName() + " failed");
			return "/user/userHomeScreen";
		}
		
//		userService.save(userForm);
		String userName = securityService.findLoggedInUsername();
		userService.joinUserToHouse(userName,house);
		
		return "/user/userHomeScreen";
	}
	@RequestMapping(value = {"/user/index2"},method = RequestMethod.GET)
	public String gateway(Model model) {
		String userName = securityService.findLoggedInUsername();
		House house = userService.findHouseForUser(userName);
		model.addAttribute("house", house);
		model.addAttribute("groceryLists", houseService.getAllGroceryListNamesForHouse(house.getHouseName()));
		return "/user/userHomeScreen";
	}
	@RequestMapping(value = {"/user/newGroceryList"},method = RequestMethod.GET)
	public String userGroceryList(Model model) {
		String userName = securityService.findLoggedInUsername();
		House house = userService.findHouseForUser(userName);
		
		house = houseService.createNewGroceryList(userName,house.getHouseName());
		model.addAttribute("house", house);
		model.addAttribute("groceryLists", houseService.getAllGroceryListNamesForHouse(house.getHouseName()));
		
		return "/user/userHomeScreen";
	}
	
	@RequestMapping(value = {"/user/getGroceryList/{groceryList}"},method = RequestMethod.GET)
	public String userGroceryList(@PathVariable("groceryList") String groceryListName,Model model) {
		String userName = securityService.findLoggedInUsername();
		House house = userService.findHouseForUser(userName);
		
//		house = houseService.createNewGroceryList(userName,house.getHouseName());
		model.addAttribute("house", house);
		model.addAttribute("groceryLists", houseService.getAllGroceryListNamesForHouse(house.getHouseName()));
		
		return "/user/groceryList";
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
