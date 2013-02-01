package edu.fairfield.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.fairfield.User;
import edu.fairfield.db.UserJDBCTemplate;

@Controller
public class UserController {

	private @Autowired ApplicationContext appContext;
	private UserJDBCTemplate userJDBCTemplate;
	
	@RequestMapping(value = "user", method = RequestMethod.GET) 
	public ModelAndView user() { 
		return new ModelAndView("user", "command", new User()); 
	}
	
	@RequestMapping(value = "createUser", method = RequestMethod.POST) 
	public String createUser(@ModelAttribute("SpringWeb")User user, ModelMap model) {
		
		String userName = user.getFirstName().charAt(0) + user.getLastName();
		// Need to encrypt password
		String password = user.getLastName();
		userJDBCTemplate = (UserJDBCTemplate)appContext.getBean("userJDBCTemplate"); 
		userJDBCTemplate.create(userName.toLowerCase(), password.toLowerCase(), user.getFirstName(), user.getMiddleName(), user.getLastName(), user.getEmail(), user.getPhoneNum()); 

		model.addAttribute("firstName", user.getFirstName()); 
		model.addAttribute("lastName", user.getLastName()); 
		model.addAttribute("userName", userName.toLowerCase());
		model.addAttribute("password", password.toLowerCase());
		return "redirect:user"; 
	}

}
