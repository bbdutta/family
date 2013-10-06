package edu.fairfield.controllers;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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
	
	private static final Logger logger = Logger.getLogger(UserController.class);

	private @Autowired ApplicationContext appContext;
	private UserJDBCTemplate userJDBCTemplate;
	
	@RequestMapping(value = "user", method = RequestMethod.GET) 
	public ModelAndView user() { 
		return new ModelAndView("user", "command", new User()); 
	}
	
	@RequestMapping(value = "createUser", method = RequestMethod.POST) 
	public ModelAndView createUser(@ModelAttribute("SpringWeb")User user, ModelMap model) {
		
		String userName = user.getFirstName().charAt(0) + user.getLastName();
		// Need to encrypt password
		String password = user.getLastName();
		userJDBCTemplate = (UserJDBCTemplate)appContext.getBean("userJDBCTemplate"); 
		userJDBCTemplate.create(userName.toLowerCase(), password.toLowerCase(), user.getFirstName(), user.getMiddleName(), user.getLastName(), user.getEmail(), user.getPhoneNum()); 

		model.addAttribute("firstName", user.getFirstName()); 
		model.addAttribute("lastName", user.getLastName()); 
		model.addAttribute("userName", userName.toLowerCase());
		model.addAttribute("password", password.toLowerCase());
		//return "redirect:user"; 
		return new ModelAndView("user", "command", user); 
	}

	@RequestMapping(value = "login", method = { RequestMethod.GET, RequestMethod.POST }) 
	public ModelAndView login() { 
		return new ModelAndView("login", "command", new User()); 
	}
	
	@RequestMapping(value = "validateLogin", method = RequestMethod.POST) 
	public String validateLogin(@ModelAttribute("SpringWeb")User user, HttpSession session, ModelMap model) {
		
		String userName = user.getUserName();
		// Need to encrypt password
		String password = user.getPassword();
		
		logger.info("UserController::validateLogin: user -> " + userName + ", password -> " + password);

		userJDBCTemplate = (UserJDBCTemplate)appContext.getBean("userJDBCTemplate"); 
		try {
		user = userJDBCTemplate.login(userName, password);
		} catch (Exception e) {
			model.addAttribute("errMsg", "Username or Password is not correct.");
			return "forward:/login"; 
		}
		
		session.setAttribute("username", user.getUserName());
		
		String fwdStr = "forward:/fsHome";

		String role = userJDBCTemplate.getUserRole(user.getUserId());
		if ("ADMIN".equals(role))
			fwdStr = "forward:/adminHome";
		else if ("REPORT".equals(role))
			fwdStr = "forward:/rptHome";
		else if ("DATA_ENTRY".equals(role)) {
			long programId = userJDBCTemplate.getUserProgramId(user.getUserId());
			if (programId == 1 && "DATA_ENTRY".equals(role))
				fwdStr = "forward:/fsHome";
			else if (programId == 2 && "DATA_ENTRY".equals(role))
				fwdStr = "forward:/bhnHome";
		}
		
		return fwdStr; 
	}
	

}
