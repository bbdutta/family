package edu.fairfield.controllers;

import java.util.LinkedHashMap;
import java.util.Map;

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

	@RequestMapping(value = "adminHome", method = RequestMethod.GET) 
	public String adminHome(ModelMap model, HttpSession session) {
		if (!session.isNew()) {
			String userSessionId = (String)session.getAttribute("UserSessionId");
			if (userSessionId == null)
				return "forward:/login"; 
			} else {
				return "forward:/login"; 
		}
		return "adminhome"; 
	}
	
	@RequestMapping(value = "addUser", method = RequestMethod.GET) 
	public ModelAndView addUser() {
		
		return new ModelAndView("adduser", "command", new User()); 
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
		model.addAttribute("USER_SAVE_STATUS", "USER_SAVE_STATUS");

		//return "redirect:user"; 
		return new ModelAndView("adduser", "command", user); 
	}

	@RequestMapping(value = "createUserRole", method = RequestMethod.POST) 
	public ModelAndView createUserRole(@ModelAttribute("SpringWeb")User user, ModelMap model) {
		
		userJDBCTemplate = (UserJDBCTemplate)appContext.getBean("userJDBCTemplate"); 
		int cnt = userJDBCTemplate.createUserRole(user.getUserId(), user.getRoleId()); 

		model.addAttribute("roleId", user.getRoleId());
		if (cnt > 0)
			model.addAttribute("ROLE_SAVE_STATUS", "ROLE_SAVE_STATUS");
		else
			model.addAttribute("ROLE_SAVE_STATUS", "ROLE_ALREADY_EXISTS");
		Map<Long,String> userList = new LinkedHashMap<Long,String>();

		for (User u : userJDBCTemplate.listUsers()) {
			userList.put(u.getUserId(), u.getUserName());
		}
		model.addAttribute("userList", userList);

		//return "redirect:user"; 
		return new ModelAndView("adduserrole", "command", user); 
	}

	@RequestMapping(value = "addUserRole", method = RequestMethod.GET) 
	public ModelAndView addUserRole(ModelMap model) {
		
		userJDBCTemplate = (UserJDBCTemplate)appContext.getBean("userJDBCTemplate"); 

		Map<Long,String> userList = new LinkedHashMap<Long,String>();
		for (User user : userJDBCTemplate.listUsers()) {
			userList.put(user.getUserId(), user.getUserName());
		}
		model.addAttribute("userList", userList);

		return new ModelAndView("adduserrole", "command", new User()); 
	}

	@RequestMapping(value = "addUserProgram", method = RequestMethod.GET) 
	public ModelAndView addUserProgram(ModelMap model) {
		
		userJDBCTemplate = (UserJDBCTemplate)appContext.getBean("userJDBCTemplate"); 

		Map<Long,String> userList = new LinkedHashMap<Long,String>();
		for (User user : userJDBCTemplate.listUsers()) {
			userList.put(user.getUserId(), user.getUserName());
		}
		model.addAttribute("userList", userList);

		return new ModelAndView("adduserprogram", "command", new User()); 
	}

	@RequestMapping(value = "createUserProgram", method = RequestMethod.POST) 
	public ModelAndView createUserProgram(@ModelAttribute("SpringWeb")User user, ModelMap model) {
		
		userJDBCTemplate = (UserJDBCTemplate)appContext.getBean("userJDBCTemplate"); 
		int cnt = userJDBCTemplate.createUserProgram(user.getUserId(), user.getProgramId()); 

		model.addAttribute("programId", user.getProgramId());
		if (cnt > 0)
			model.addAttribute("PROGRAM_SAVE_STATUS", "PROGRAM_SAVE_STATUS");
		else
			model.addAttribute("PROGRAM_SAVE_STATUS", "PROGRAM_ALREADY_EXISTS");
		Map<Long,String> userList = new LinkedHashMap<Long,String>();

		for (User u : userJDBCTemplate.listUsers()) {
			userList.put(u.getUserId(), u.getUserName());
		}
		model.addAttribute("userList", userList);

		//return "redirect:user"; 
		return new ModelAndView("adduserprogram", "command", user); 
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
			
			session.setAttribute("username", user.getUserName());
			session.setAttribute("UserSessionId", user.getUserName());
			
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
		} catch (Exception e) {
			logger.error("UserController::validateLogin: user -> " + userName + ", password -> " + password);
			logger.error("UserController::validateLogin: error -> " + e.getMessage());
			model.addAttribute("errMsg", "Incorrect Username/Password");
			return "forward:/login"; 
		}

	}
	

}
