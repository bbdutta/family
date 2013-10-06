package edu.fairfield.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.fairfield.Client;
import edu.fairfield.Employment;
import edu.fairfield.GoalService;
import edu.fairfield.db.CaseJDBCTemplate;
import edu.fairfield.db.ClientJDBCTemplate;

@Controller
public class CaseController {
	
	private @Autowired ApplicationContext appContext;
	private CaseJDBCTemplate caseJDBCTemplate;
	
	@RequestMapping(value = "fsCaseManagement", method = { RequestMethod.GET, RequestMethod.POST }) 
	public ModelAndView fsCaseManagement(@RequestParam(value="inmate-num") long inmateNum,
			@RequestParam(value="program-id") long programId, 
			ModelMap model) {
		
		ClientJDBCTemplate clientJDBCTemplate = (ClientJDBCTemplate)appContext.getBean("clientJDBCTemplate");
		Client client = null;
		try {
			client = clientJDBCTemplate.getClient(inmateNum, programId);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		if(client == null) {
			model.addAttribute("CASE_AVAILABLE", "The Given Inmate Number "+inmateNum+" is Not available, Please Add The client: ");
			return new ModelAndView("forward:/fsHome");
		}
		long clientId = client.getClientId();
		System.out.println("clientId: " + clientId);
		caseJDBCTemplate = (CaseJDBCTemplate)appContext.getBean("caseJDBCTemplate"); 
		model.addAttribute("goalList", caseJDBCTemplate.getGoals());
		model.addAttribute("serviceList", caseJDBCTemplate.getGoalServices());
		model.addAttribute("inmateNum", inmateNum);

		Employment employment = caseJDBCTemplate.getEmployment(client.getClientId(), programId);
		if(employment == null) {
			employment = new Employment();
			employment.setClientId(clientId);
			employment.setInmateNum(inmateNum);
			employment.setProgramId(programId);
		} else {
			employment.setSelectedGoalService(caseJDBCTemplate.getSelectedGoalServices(employment.getEmploymentId()));
		}

		model.addAttribute("clientId", client.getClientId());
		model.addAttribute("inmateNum", client.getInmateNum());
		model.addAttribute("lastName", client.getLastName());
		if (inmateNum !=0)
			return new ModelAndView("fscasemanagement", "command", employment); 
		else
			return new ModelAndView("fsintake", "command", new Client()); 
	}
	
	@RequestMapping(value = "confirmCase", method = RequestMethod.POST) 
	public ModelAndView confirmCase(@ModelAttribute("SpringWeb")Employment employment, ModelMap model) {
		
		if(employment.getSelectedGoalService() != null){
			int counter = 0;
			List<Integer> gsEmpty = new ArrayList<Integer>();
			System.out.println("Before removing goals");
			for(GoalService gs:employment.getSelectedGoalService()) {
				System.out.println("Inside Goals");
				System.out.println("G: " + gs.getGoalName());
				System.out.println("S: " + gs.getServiceName());
				if(("".equals(gs.getGoalName()) || gs.getGoalName() == null) && ("".equals(gs.getServiceName()) || gs.getServiceName() == null)) {
					System.out.println("Inside empty goal");
					gsEmpty.add(counter);
				}
				counter++;
			}
			for(Integer gsE:gsEmpty) {
				employment.getSelectedGoalService().remove(gsE);
			}
		}
		
		ClientJDBCTemplate clientJDBCTemplate = (ClientJDBCTemplate)appContext.getBean("clientJDBCTemplate");
		Client client = clientJDBCTemplate.getClient(employment.getInmateNum(), employment.getProgramId());
		model.addAttribute("clientId", employment.getClientId());
		model.addAttribute("inmateNum", client.getInmateNum());
		model.addAttribute("lastName", client.getLastName());
		model.addAttribute("CASE_SAVE_STATUS", "");
		return new ModelAndView("fscaseconfirm", "command", employment); 
	}
	
	@RequestMapping(value = "backCase", method = RequestMethod.POST) 
	public ModelAndView backCase(@ModelAttribute("SpringWeb")Employment employment, ModelMap model) {
		
		if(employment.getSelectedGoalService() != null){
			int counter = 0;
			List<Integer> gsEmpty = new ArrayList<Integer>();
			System.out.println("Before removing goals");
			for(GoalService gs:employment.getSelectedGoalService()) {
				System.out.println("Inside Goals");
				System.out.println("G: " + gs.getGoalName());
				System.out.println("S: " + gs.getServiceName());
				if(("".equals(gs.getGoalName()) || gs.getGoalName() == null) && ("".equals(gs.getServiceName()) || gs.getServiceName() == null)) {
					System.out.println("Inside empty goal");
					gsEmpty.add(counter);
				}
				counter++;
			}
			for(Integer gsE:gsEmpty) {
				employment.getSelectedGoalService().remove(gsE);
			}
		}
		
		ClientJDBCTemplate clientJDBCTemplate = (ClientJDBCTemplate)appContext.getBean("clientJDBCTemplate");
		Client client = clientJDBCTemplate.getClient(employment.getInmateNum(), employment.getProgramId());
		model.addAttribute("clientId", employment.getClientId());
		model.addAttribute("inmateNum", client.getInmateNum());
		model.addAttribute("lastName", client.getLastName());
		model.addAttribute("CASE_SAVE_STATUS", "");
		return new ModelAndView("fscasemanagement", "command", employment); 
	}

	@RequestMapping(value = "addCase", method = RequestMethod.POST) 
	public ModelAndView addCase(@ModelAttribute("SpringWeb")Employment employment, ModelMap model) {
		
		caseJDBCTemplate = (CaseJDBCTemplate)appContext.getBean("caseJDBCTemplate"); 
		caseJDBCTemplate.create(employment); 
		model.addAttribute("goalList", caseJDBCTemplate.getGoals());
		model.addAttribute("serviceList", caseJDBCTemplate.getGoalServices());
		ClientJDBCTemplate clientJDBCTemplate = (ClientJDBCTemplate)appContext.getBean("clientJDBCTemplate");
		Client client = clientJDBCTemplate.getClient(employment.getInmateNum(), employment.getProgramId());
		model.addAttribute("clientId", employment.getClientId());
		model.addAttribute("inmateNum", client.getInmateNum());
		model.addAttribute("lastName", client.getLastName());
		model.addAttribute("CASE_SAVE_STATUS", "Case details are saved successfully.");
		return new ModelAndView("fscaseconfirm", "command", employment);
	}

	@RequestMapping(value = "bhnCaseManagement", method = { RequestMethod.GET, RequestMethod.POST }) 
	public ModelAndView bhnCaseManagement(@RequestParam(value="inmate-num") long inmateNum,
			@RequestParam(value="program-id") long programId, 
			ModelMap model) {
		
		ClientJDBCTemplate clientJDBCTemplate = (ClientJDBCTemplate)appContext.getBean("clientJDBCTemplate");
		Client client = null;
		try {
			client = clientJDBCTemplate.getClient(inmateNum, programId);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		if(client == null) {
			model.addAttribute("CASE_AVAILABLE", "The Given Inmate Number "+inmateNum+" is Not available, Please Add The client: ");
			return new ModelAndView("forward:/bhnHome");
		}
		long clientId = client.getClientId();
		System.out.println("clientId: " + clientId);
		caseJDBCTemplate = (CaseJDBCTemplate)appContext.getBean("caseJDBCTemplate"); 
		model.addAttribute("goalList", caseJDBCTemplate.getGoals());
		model.addAttribute("serviceList", caseJDBCTemplate.getGoalServices());
		model.addAttribute("inmateNum", inmateNum);

		Employment employment = caseJDBCTemplate.getEmployment(client.getClientId(), programId);
		if(employment == null) {
			employment = new Employment();
			employment.setClientId(clientId);
			employment.setInmateNum(inmateNum);
			employment.setProgramId(programId);
		} else {
			employment.setSelectedGoalService(caseJDBCTemplate.getSelectedGoalServices(employment.getEmploymentId()));
		}

		model.addAttribute("clientId", client.getClientId());
		model.addAttribute("inmateNum", client.getInmateNum());
		model.addAttribute("lastName", client.getLastName());
		if (inmateNum !=0)
			return new ModelAndView("bhncasemanagement", "command", employment); 
		else
			return new ModelAndView("bhnintake", "command", new Client()); 
	}

	@RequestMapping(value = "confirmBhnCase", method = RequestMethod.POST) 
	public ModelAndView confirmBhnCase(@ModelAttribute("SpringWeb")Employment employment, ModelMap model) {
		
		ClientJDBCTemplate clientJDBCTemplate = (ClientJDBCTemplate)appContext.getBean("clientJDBCTemplate");
		Client client = clientJDBCTemplate.getClient(employment.getInmateNum(), employment.getProgramId());
		model.addAttribute("clientId", employment.getClientId());
		model.addAttribute("inmateNum", client.getInmateNum());
		model.addAttribute("lastName", client.getLastName());
		model.addAttribute("CASE_SAVE_STATUS", "");
		return new ModelAndView("bhncaseconfirm", "command", employment); 
	}

	@RequestMapping(value = "backBhnCase", method = RequestMethod.POST) 
	public ModelAndView backBhnCase(@ModelAttribute("SpringWeb")Employment employment, ModelMap model) {
		
		ClientJDBCTemplate clientJDBCTemplate = (ClientJDBCTemplate)appContext.getBean("clientJDBCTemplate");
		Client client = clientJDBCTemplate.getClient(employment.getInmateNum(), employment.getProgramId());
		model.addAttribute("clientId", employment.getClientId());
		model.addAttribute("inmateNum", client.getInmateNum());
		model.addAttribute("lastName", client.getLastName());
		model.addAttribute("CASE_SAVE_STATUS", "");
		return new ModelAndView("bhncasemanagement", "command", employment); 
	}

	@RequestMapping(value = "addBhnCase", method = RequestMethod.POST) 
	public ModelAndView addBhnCase(@ModelAttribute("SpringWeb")Employment employment, ModelMap model) {
		
		caseJDBCTemplate = (CaseJDBCTemplate)appContext.getBean("caseJDBCTemplate"); 
		employment.setDateOfHire(null);
		caseJDBCTemplate.create(employment); 
		model.addAttribute("goalList", caseJDBCTemplate.getGoals());
		model.addAttribute("serviceList", caseJDBCTemplate.getGoalServices());
		ClientJDBCTemplate clientJDBCTemplate = (ClientJDBCTemplate)appContext.getBean("clientJDBCTemplate");
		Client client = clientJDBCTemplate.getClient(employment.getInmateNum(), employment.getProgramId());
		model.addAttribute("clientId", employment.getClientId());
		model.addAttribute("inmateNum", client.getInmateNum());
		model.addAttribute("lastName", client.getLastName());
		model.addAttribute("CASE_SAVE_STATUS", "Case details are saved successfully.");
		return new ModelAndView("bhncaseconfirm", "command", employment);
	}

}
