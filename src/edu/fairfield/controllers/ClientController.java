package edu.fairfield.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
import edu.fairfield.db.ClientJDBCTemplate;

@Controller
public class ClientController {
	
	private @Autowired ApplicationContext appContext;
	private ClientJDBCTemplate clientJDBCTemplate;
	
	@RequestMapping(value = "client", method = RequestMethod.GET) 
	public ModelAndView client() { 
		return new ModelAndView("client", "command", new Client()); 
	}
	
	@RequestMapping(value = "fsHome", method = { RequestMethod.GET, RequestMethod.POST }) 
	public String fsHome(ModelMap model) {
		
		clientJDBCTemplate = (ClientJDBCTemplate)appContext.getBean("clientJDBCTemplate"); 

		model.addAttribute("clientList", clientJDBCTemplate.listClients(1L));

		return "fshome"; 
		
	}

	@RequestMapping(value = "fsIntake", method = { RequestMethod.GET, RequestMethod.POST }) 
	public ModelAndView fsIntake(@RequestParam(value="inmate-num") long inmateNum,
			@RequestParam(value="program-id") long programId, 
			ModelMap model) { 

		model.addAttribute("programId", programId);
		model.addAttribute("raceList", Client.raceList);
		model.addAttribute("refSrcList", Client.refSrcList);
		model.addAttribute("eduList", Client.eduList);

		clientJDBCTemplate = (ClientJDBCTemplate)appContext.getBean("clientJDBCTemplate");
		Client client = null;		
		if (inmateNum !=0)
		{
			try
			{
				client = clientJDBCTemplate.getClient(inmateNum, programId);
			} catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
			if(client == null) 
			{
				model.addAttribute("INTAKE_AVAILABLE", "The Given Inmate Number "+inmateNum+" is Not available, Please Add The client: ");
				return new ModelAndView("forward:/fsHome");
			} else 
			{
				model.addAttribute("inmateNum", inmateNum);
				return new ModelAndView("fsintake", "command", clientJDBCTemplate.getClient(inmateNum, programId));
			}
		}	
		else	
			return new ModelAndView("fsintake", "command", new Client()); 
	}

	@RequestMapping(value = "confirmFsIntake", method = RequestMethod.POST) 
	public ModelAndView confirmFsIntake(@ModelAttribute("SpringWeb")Client client, ModelMap model) {
		
		clientJDBCTemplate = (ClientJDBCTemplate)appContext.getBean("clientJDBCTemplate");
		model.addAttribute("clientId", client.getClientId());
		model.addAttribute("programId", client.getProgramId());
		model.addAttribute("INTAKE_SAVE_STATUS", "");
		return new ModelAndView("fsintakeconfirm", "command", client); 
	}

	@RequestMapping(value = "backFsIntake", method = RequestMethod.POST) 
	public ModelAndView backFsIntake(@ModelAttribute("SpringWeb")Client client, ModelMap model) {
		
		clientJDBCTemplate = (ClientJDBCTemplate)appContext.getBean("clientJDBCTemplate");
		model.addAttribute("clientId", client.getClientId());
		model.addAttribute("programId", client.getProgramId());
		model.addAttribute("raceList", Client.raceList);
		model.addAttribute("refSrcList", Client.refSrcList);
		model.addAttribute("eduList", Client.eduList);
		model.addAttribute("INTAKE_SAVE_STATUS", "");
		return new ModelAndView("fsintake", "command", client); 
	}
	
	
	
	@RequestMapping(value = "addFsClient", method = RequestMethod.POST) 
	public ModelAndView addFsClient(@ModelAttribute("SpringWeb")Client client, ModelMap model) {
		
		SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar dob = Calendar.getInstance();
		Calendar doa = Calendar.getInstance();
		try {
			dob.setTime(dateSdf.parse(client.getDob()));
			doa.setTime(dateSdf.parse(client.getAdmittedOn()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		clientJDBCTemplate = (ClientJDBCTemplate)appContext.getBean("clientJDBCTemplate");
		if (client.getClientId() == 0) {
			clientJDBCTemplate.create(client.getInmateNum(),  client.getFirstName(), client.getMiddleName(), client.getLastName(), 
					client.getGender(), dob, client.getEducationLevel(), doa, client.getRace(), client.getReferralSource(), client.getTanfEligible(), client.getProgramId()); 
		} else {
			clientJDBCTemplate.update(client.getInmateNum(),  client.getFirstName(), client.getMiddleName(), client.getLastName(), 
					client.getGender(), dob, client.getEducationLevel(), doa, client.getRace(), client.getReferralSource(), client.getTanfEligible(), client.getClientId(), client.getProgramId()); 
		}
		model.addAttribute("clientId", client.getClientId());
		model.addAttribute("programId", client.getProgramId());
		model.addAttribute("raceList", Client.raceList);
		model.addAttribute("refSrcList", Client.refSrcList);
		model.addAttribute("eduList", Client.eduList);
		model.addAttribute("INTAKE_SAVE_STATUS", "INTAKE_SAVE_STATUS");
		return new ModelAndView("fsintakeconfirm", "command", client); 
	}
	
	@RequestMapping(value = "fsDischarge", method = { RequestMethod.GET, RequestMethod.POST }) 
	public ModelAndView fsDischarge(@RequestParam(value="inmate-num") long inmateNum,
			@RequestParam(value="program-id") long programId, 
			ModelMap model) { 

		model.addAttribute("eduList", Client.eduList);
		model.addAttribute("housingList", Client.housingList);
		model.addAttribute("numList", Client.numList);
		model.addAttribute("dischargeList", Client.dischargeList);
		model.addAttribute("dismissList", Client.dismissList);
		
		ClientJDBCTemplate clientJDBCTemplate = (ClientJDBCTemplate)appContext.getBean("clientJDBCTemplate");
		Client client = null;


			if (inmateNum !=0) {
				try {
					client = clientJDBCTemplate.getClient(inmateNum, programId);
				} catch(Exception ex) {
					ex.printStackTrace();
				}

				if(client == null) {
					model.addAttribute("DISCHARGE_AVAILABLE", "The Given Inmate Number "+inmateNum+" is Not available, Please Add The client: ");
					return new ModelAndView("forward:/fsHome");
				} else {
					model.addAttribute("inmateNum", client.getInmateNum());
					model.addAttribute("lastName", client.getLastName());
					return new ModelAndView("fsdischarge", "command", client);
				}
			}
		else
			return new ModelAndView("fsintake", "command", new Client()); 
	}

	@RequestMapping(value = "confirmFsDischarge", method = RequestMethod.POST) 
	public ModelAndView confirmFsDischarge(@ModelAttribute("SpringWeb")Client client, ModelMap model) {
		
		SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar dod = Calendar.getInstance();
		
		try {
			dod.setTime(dateSdf.parse(client.getDod()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		model.addAttribute("clientId", client.getClientId());
//		model.addAttribute("inmateNum", client.getInmateNum());
//		model.addAttribute("lastName", client.getLastName());
		model.addAttribute("DISCHARGE_SAVE_STATUS", "");
		return new ModelAndView("fsconfirmdischarge", "command", client); 
	}

	@RequestMapping(value = "addFsDischarge", method = RequestMethod.POST) 
	public ModelAndView addDischarge(@ModelAttribute("SpringWeb")Client client, ModelMap model) {
		
		SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar dod = Calendar.getInstance();
		
		try {
			dod.setTime(dateSdf.parse(client.getDod()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		clientJDBCTemplate = (ClientJDBCTemplate)appContext.getBean("clientJDBCTemplate"); 
		clientJDBCTemplate.addDischarge(client.getClientId(), client.getProgramId(), dod, client.getLengthofStay(), 
				client.getAppointments(), client.getNumdrugtests(), client.getTestoncepermonth(),client.getNumpositive(),
				client.getDischargeplan(),client.getCommlinkages(),client.getEducationLevel(),client.getHousing(),
				client.getDischargeReason(),client.getDismissReason(), null, null, null);
		model.addAttribute("clientId", client.getClientId());
		model.addAttribute("eduList", Client.eduList);
		model.addAttribute("housingList", Client.housingList);
		model.addAttribute("numList", Client.numList);
		model.addAttribute("dischargeList", Client.dischargeList);
		model.addAttribute("dismissList", Client.dismissList);

		model.addAttribute("DISCHARGE_SAVE_STATUS", "Discharge information saved successfully.");
		return new ModelAndView("fsconfirmdischarge", "command", client); 
	}
	
	@RequestMapping(value = "backFsDischarge", method = RequestMethod.POST) 
	public ModelAndView backFsDischarge(@ModelAttribute("SpringWeb")Client client, ModelMap model) {
		
		model.addAttribute("eduList", Client.eduList);
		model.addAttribute("housingList", Client.housingList);
		model.addAttribute("numList", Client.numList);
		model.addAttribute("dischargeList", Client.dischargeList);
		model.addAttribute("dismissList", Client.dismissList);

		SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar dod = Calendar.getInstance();
		
		try {
			dod.setTime(dateSdf.parse(client.getDod()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		model.addAttribute("clientId", client.getClientId());
		model.addAttribute("DISCHARGE_SAVE_STATUS", "");
		return new ModelAndView("fsdischarge", "command", client); 
	}
	
	@RequestMapping(value = "editFsClient", method = RequestMethod.POST) 
	public ModelAndView editFsClient(@RequestParam(value="client-id") long clientId,
			@RequestParam(value="program-id") long programId, 
			ModelMap model) { 
		clientJDBCTemplate = (ClientJDBCTemplate)appContext.getBean("clientJDBCTemplate");
		model.addAttribute("clientId", clientId);
		model.addAttribute("programId", programId);
		return new ModelAndView("fsintake", "command", clientJDBCTemplate.getClient(clientId, programId));
	}
	
	@RequestMapping(value = "bhnHome", method = { RequestMethod.GET, RequestMethod.POST }) 
	public String bhnHome(ModelMap model) {
		
		clientJDBCTemplate = (ClientJDBCTemplate)appContext.getBean("clientJDBCTemplate"); 

		model.addAttribute("clientList", clientJDBCTemplate.listClients(2L));

		return "bhnhome"; 
	}

	@RequestMapping(value = "bhnIntake", method = { RequestMethod.GET, RequestMethod.POST }) 
	public ModelAndView bhnIntake(@RequestParam(value="inmate-num") long inmateNum,
			@RequestParam(value="program-id") long programId, 
			ModelMap model) { 

		model.addAttribute("programId", programId);
		model.addAttribute("raceList", Client.raceList);
		model.addAttribute("refSrcList", Client.refSrcList);
		model.addAttribute("eduList", Client.eduList);

		clientJDBCTemplate = (ClientJDBCTemplate)appContext.getBean("clientJDBCTemplate");
		Client client = null;		
		if (inmateNum !=0)
		{
			try
			{
				client = clientJDBCTemplate.getClient(inmateNum, programId);
			} catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
			if(client == null) 
			{
				model.addAttribute("INTAKE_AVAILABLE", "The Given Inmate Number "+inmateNum+" is Not available, Please Add The client: ");
				return new ModelAndView("forward:/bhnHome");
			} else 
			{
				model.addAttribute("inmateNum", inmateNum);
				return new ModelAndView("bhnintake", "command", clientJDBCTemplate.getClient(inmateNum, programId));
			}
		}	
		else	
			return new ModelAndView("bhnintake", "command", new Client()); 
	}

	@RequestMapping(value = "confirmBhnIntake", method = RequestMethod.POST) 
	public ModelAndView confirmBhnIntake(@ModelAttribute("SpringWeb")Client client, ModelMap model) {
		
		clientJDBCTemplate = (ClientJDBCTemplate)appContext.getBean("clientJDBCTemplate");
		model.addAttribute("clientId", client.getClientId());
		model.addAttribute("programId", client.getProgramId());
		model.addAttribute("INTAKE_SAVE_STATUS", "");
		return new ModelAndView("bhnintakeconfirm", "command", client); 
	}

	@RequestMapping(value = "backBhnIntake", method = RequestMethod.POST) 
	public ModelAndView backBhnIntake(@ModelAttribute("SpringWeb")Client client, ModelMap model) {
		
		clientJDBCTemplate = (ClientJDBCTemplate)appContext.getBean("clientJDBCTemplate");
		model.addAttribute("clientId", client.getClientId());
		model.addAttribute("programId", client.getProgramId());
		model.addAttribute("raceList", Client.raceList);
		model.addAttribute("refSrcList", Client.refSrcList);
		model.addAttribute("eduList", Client.eduList);
		model.addAttribute("INTAKE_SAVE_STATUS", "");
		return new ModelAndView("bhnintake", "command", client); 
	}

	@RequestMapping(value = "addBhnClient", method = RequestMethod.POST) 
	public ModelAndView addBhnClient(@ModelAttribute("SpringWeb")Client client, ModelMap model) {
		
		SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar dob = Calendar.getInstance();
		Calendar doa = Calendar.getInstance();
		try {
			dob.setTime(dateSdf.parse(client.getDob()));
			doa.setTime(dateSdf.parse(client.getAdmittedOn()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		clientJDBCTemplate = (ClientJDBCTemplate)appContext.getBean("clientJDBCTemplate");
		if (client.getClientId() == 0) {
			clientJDBCTemplate.create(client.getInmateNum(),  client.getFirstName(), client.getMiddleName(), client.getLastName(), 
					client.getGender(), dob, client.getEducationLevel(), doa, client.getRace(), client.getReferralSource(), client.getTanfEligible(), client.getProgramId()); 
		} else {
			clientJDBCTemplate.update(client.getInmateNum(),  client.getFirstName(), client.getMiddleName(), client.getLastName(), 
					client.getGender(), dob, client.getEducationLevel(), doa, client.getRace(), client.getReferralSource(), client.getTanfEligible(), client.getClientId(), client.getProgramId()); 
		}
		model.addAttribute("clientId", client.getClientId());
		model.addAttribute("programId", client.getProgramId());
		model.addAttribute("raceList", Client.raceList);
		model.addAttribute("refSrcList", Client.refSrcList);
		model.addAttribute("eduList", Client.eduList);
		model.addAttribute("INTAKE_SAVE_STATUS", "INTAKE_SAVE_STATUS");
		return new ModelAndView("bhnintakeconfirm", "command", client); 
	}

	@RequestMapping(value = "bhnDischarge", method = { RequestMethod.GET, RequestMethod.POST }) 
	public ModelAndView bhnDischarge(@RequestParam(value="inmate-num") long inmateNum,
			@RequestParam(value="program-id") long programId, 
			ModelMap model) { 

		model.addAttribute("eduList", Client.eduList);
		model.addAttribute("housingList", Client.housingList);
		model.addAttribute("numList", Client.numList);
		model.addAttribute("dischargeList", Client.dischargeList);
		model.addAttribute("dismissList", Client.dismissList);
		model.addAttribute("trtModalityList", Client.trtModalityList);
		model.addAttribute("medicationAssistedList", Client.medicationAssistedList);
		
		ClientJDBCTemplate clientJDBCTemplate = (ClientJDBCTemplate)appContext.getBean("clientJDBCTemplate");
		Client client = null;


			if (inmateNum !=0) {
				try {
					client = clientJDBCTemplate.getClient(inmateNum, programId);
				} catch(Exception ex) {
					ex.printStackTrace();
				}

				if(client == null) {
					model.addAttribute("DISCHARGE_AVAILABLE", "The Given Inmate Number "+inmateNum+" is Not available, Please Add The client: ");
					return new ModelAndView("forward:/bhnHome");
				} else {
					model.addAttribute("inmateNum", client.getInmateNum());
					model.addAttribute("lastName", client.getLastName());
					return new ModelAndView("bhndischarge", "command", client);
				}
			}
		else
			return new ModelAndView("bhnintake", "command", new Client()); 
	}

	@RequestMapping(value = "confirmBhnDischarge", method = RequestMethod.POST) 
	public ModelAndView confirmBhnDischarge(@ModelAttribute("SpringWeb")Client client, ModelMap model) {
		
		SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar dod = Calendar.getInstance();
		
		try {
			dod.setTime(dateSdf.parse(client.getDod()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		model.addAttribute("clientId", client.getClientId());
		model.addAttribute("inmateNum", client.getInmateNum());
		model.addAttribute("lastName", client.getLastName());
		model.addAttribute("DISCHARGE_SAVE_STATUS", "");
		return new ModelAndView("bhnconfirmdischarge", "command", client); 
	}

	@RequestMapping(value = "addBhnDischarge", method = RequestMethod.POST) 
	public ModelAndView addBhnDischarge(@ModelAttribute("SpringWeb")Client client, ModelMap model) {
		
		SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar dod = Calendar.getInstance();
		
		try {
			dod.setTime(dateSdf.parse(client.getDod()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		clientJDBCTemplate = (ClientJDBCTemplate)appContext.getBean("clientJDBCTemplate"); 
		clientJDBCTemplate.addDischarge(client.getClientId(), client.getProgramId(), dod, client.getLengthofStay(), 
				client.getAppointments(), client.getNumdrugtests(), client.getTestoncepermonth(),client.getNumpositive(),
				client.getDischargeplan(),client.getCommlinkages(),client.getEducationLevel(),client.getHousing(),
				client.getDischargeReason(),client.getDismissReason(), client.getTrtModality(), client.getMedicationAssisted(), client.getValidatedTrt());
		model.addAttribute("clientId", client.getClientId());
		model.addAttribute("eduList", Client.eduList);
		model.addAttribute("housingList", Client.housingList);
		model.addAttribute("numList", Client.numList);
		model.addAttribute("dischargeList", Client.dischargeList);
		model.addAttribute("dismissList", Client.dismissList);
		model.addAttribute("trtModalityList", Client.trtModalityList);
		model.addAttribute("medicationAssistedList", Client.medicationAssistedList);

		model.addAttribute("DISCHARGE_SAVE_STATUS", "Discharge information saved successfully.");
		return new ModelAndView("bhnconfirmdischarge", "command", client); 
	}

	@RequestMapping(value = "backBhnDischarge", method = RequestMethod.POST) 
	public ModelAndView backBhnDischarge(@ModelAttribute("SpringWeb")Client client, ModelMap model) {
		
		model.addAttribute("eduList", Client.eduList);
		model.addAttribute("housingList", Client.housingList);
		model.addAttribute("numList", Client.numList);
		model.addAttribute("dischargeList", Client.dischargeList);
		model.addAttribute("dismissList", Client.dismissList);
		model.addAttribute("trtModalityList", Client.trtModalityList);
		model.addAttribute("medicationAssistedList", Client.medicationAssistedList);

		SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar dod = Calendar.getInstance();
		
		try {
			dod.setTime(dateSdf.parse(client.getDod()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		model.addAttribute("clientId", client.getClientId());
		model.addAttribute("DISCHARGE_SAVE_STATUS", "");
		return new ModelAndView("bhndischarge", "command", client); 
	}

}
