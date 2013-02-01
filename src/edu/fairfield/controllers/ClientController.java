package edu.fairfield.controllers;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.fairfield.Client;
import edu.fairfield.db.ClientJDBCTemplate;

@Controller
public class ClientController {
	
	private @Autowired ApplicationContext appContext;
	private ClientJDBCTemplate clientJDBCTemplate;
	
	@RequestMapping(value = "client", method = RequestMethod.GET) 
	public ModelAndView client() { 
		return new ModelAndView("client", "command", new Client()); 
	}
	
	@RequestMapping(value = "addClient", method = RequestMethod.POST) 
	public String addClient(@ModelAttribute("SpringWeb")Client client, ModelMap model) {
		
		clientJDBCTemplate = (ClientJDBCTemplate)appContext.getBean("clientJDBCTemplate"); 
		clientJDBCTemplate.create(client.getInmateNum(),  client.getFirstName(), client.getMiddleName(), client.getLastName(), client.getGender(), Calendar.getInstance(), client.getEducationLevel()); 

		model.addAttribute("firstName", client.getFirstName()); 
		model.addAttribute("lastName", client.getLastName()); 
		model.addAttribute("inmateNumber", client.getInmateNum()); 
		return "result"; 
	}

}
