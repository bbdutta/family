package edu.fairfield.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.fairfield.Client;
import edu.fairfield.DischargePlanRpt;
import edu.fairfield.ProgramCompRpt;
import edu.fairfield.RsatRpt;
import edu.fairfield.SubstanceFreeRpt;
import edu.fairfield.TreatmentCompRpt;
import edu.fairfield.db.ReportJDBCTemplate;

@Controller
public class ReportController {
	
	private @Autowired ApplicationContext appContext;
	private ReportJDBCTemplate reportJDBCTemplate;
	private static final Logger logger = Logger.getLogger(ReportController.class);
	
	@RequestMapping(value = "rptHome", method = { RequestMethod.GET, RequestMethod.POST }) 
	public String rptHome(ModelMap model) {
		
		return "rpthome"; 
	}

	@RequestMapping(value = "viewProfileRpt", method = RequestMethod.POST) 
	public ModelAndView viewProfileRpt(@RequestParam(value="client-id") String clientId, ModelMap model) { 
		reportJDBCTemplate = (ReportJDBCTemplate)appContext.getBean("reportJDBCTemplate");
		model.addAttribute("clientId", clientId);
		return new ModelAndView("rptprofile", "command", reportJDBCTemplate.getClient(Long.parseLong(clientId)));
	}

	@RequestMapping(value = "genRpt", method = RequestMethod.POST) 
	public ModelAndView genRpt(	@RequestParam(value="rep-name") String repName,
								@RequestParam(value="start-date") String startDate,
								@RequestParam(value="end-date") String endDate,
								@RequestParam(value="evidence", required = false) String evidence,
								@RequestParam(value="evidenceTreatment", required = false) String evidenceTreatment,
								@RequestParam(value="noOfDirectStaff", required = false) String noOfDirectStaff,
								@RequestParam(value="noOfMentalHealthFacility", required = false) long noOfMentalHealthFacility,
								@RequestParam(value="noOfSunstanceAbuseFacility", required = false) long noOfSunstanceAbuseFacility,
								@RequestParam(value="noOfPrimaryCareFacility", required = false) long noOfPrimaryCareFacility,
								@RequestParam(value="order-by", required = false) String orderBy,
								@RequestParam(value="rep-fmt", required = false) String repFmt,
								ModelAndView modelAndView) { 
		
		logger.info("ReportController::genRpt: Report -> "+ repName +" : startDate -> "+ repName +" : startDate -> "+ startDate +" : endDate -> "+ endDate);
		
        Map<String,Object> parameterMap = new HashMap<String,Object>();
        
        reportJDBCTemplate = (ReportJDBCTemplate)appContext.getBean("reportJDBCTemplate");
        List<Client> clientList = null;
        List<SubstanceFreeRpt> substanceFreeRpt = null;
        List<DischargePlanRpt> dischargePlanRpt = null;
        List<TreatmentCompRpt> treatmentCompRpt = null;
        List<ProgramCompRpt> programCompRpt = null;
        List<RsatRpt> rsatRpt = null;
        JRDataSource JRdataSource = null;
		
        if ("FS_CLIENT_LIST".equals(repName)) {
        	clientList = reportJDBCTemplate.listClients(1L, startDate, endDate, orderBy);
        	JRdataSource = new JRBeanCollectionDataSource(clientList);
            parameterMap.put("rptdatasource", JRdataSource);
            if ("PDF".equals(repFmt))
            	modelAndView = new ModelAndView("pdfFSClientReport", parameterMap);
        }
        if ("FS_SUB_FREE".equals(repName)) {
        	substanceFreeRpt = reportJDBCTemplate.generateSubFreeRpt(1L, startDate, endDate);
        	JRdataSource = new JRBeanCollectionDataSource(substanceFreeRpt);
            parameterMap.put("rptdatasource", JRdataSource);
            if ("PDF".equals(repFmt))
            	modelAndView = new ModelAndView("pdfFSSubFreeReport", parameterMap);
        }
        if ("FS_DIS_PLN".equals(repName)) {
        	dischargePlanRpt = reportJDBCTemplate.generateDischargePlanRpt(1L, startDate, endDate);
        	JRdataSource = new JRBeanCollectionDataSource(dischargePlanRpt);
            parameterMap.put("rptdatasource", JRdataSource);
            if ("PDF".equals(repFmt))
            	modelAndView = new ModelAndView("pdfFSDisPlnReport", parameterMap);
        }
        if ("FS_TRT_COMP".equals(repName)) {
        	treatmentCompRpt = reportJDBCTemplate.generateTreatmentCompRpt(1L, startDate, endDate);
        	JRdataSource = new JRBeanCollectionDataSource(treatmentCompRpt);
            parameterMap.put("rptdatasource", JRdataSource);
            if ("PDF".equals(repFmt))
            	modelAndView = new ModelAndView("pdfFSTrtCompReport", parameterMap);
        }
        if ("FS_PROG_COMP".equals(repName)) {
        	programCompRpt = reportJDBCTemplate.generateProgramCompRpt(1L, startDate, endDate);
        	JRdataSource = new JRBeanCollectionDataSource(programCompRpt);
            parameterMap.put("rptdatasource", JRdataSource);
            if ("PDF".equals(repFmt))
            	modelAndView = new ModelAndView("pdfFSProgCompReport", parameterMap);
        }
        if ("BHN_CLIENT_LIST".equals(repName)) {
        	clientList = reportJDBCTemplate.listClients(2L, startDate, endDate, orderBy);
        	JRdataSource = new JRBeanCollectionDataSource(clientList);
            parameterMap.put("rptdatasource", JRdataSource);
            if ("PDF".equals(repFmt))
            	modelAndView = new ModelAndView("pdfBHNClientReport", parameterMap);
            if ("XLS".equals(repFmt))
            	modelAndView = new ModelAndView("xlsBHNClientReport", parameterMap);
        }
        if ("BHN_SUB_FREE".equals(repName)) {
        	substanceFreeRpt = reportJDBCTemplate.generateSubFreeRpt(2L, startDate, endDate);
        	JRdataSource = new JRBeanCollectionDataSource(substanceFreeRpt);
            parameterMap.put("rptdatasource", JRdataSource);
            if ("PDF".equals(repFmt))
            	modelAndView = new ModelAndView("pdfBHNSubFreeReport", parameterMap);
        }
        if ("BHN_DIS_PLN".equals(repName)) {
        	dischargePlanRpt = reportJDBCTemplate.generateDischargePlanRpt(2L, startDate, endDate);
        	JRdataSource = new JRBeanCollectionDataSource(dischargePlanRpt);
            parameterMap.put("rptdatasource", JRdataSource);
            if ("PDF".equals(repFmt))
            	modelAndView = new ModelAndView("pdfBHNDisPlnReport", parameterMap);
        }
        if ("BHN_TRT_COMP".equals(repName)) {
        	treatmentCompRpt = reportJDBCTemplate.generateTreatmentCompRpt(2L, startDate, endDate);
        	JRdataSource = new JRBeanCollectionDataSource(treatmentCompRpt);
            parameterMap.put("rptdatasource", JRdataSource);
            if ("PDF".equals(repFmt))
            	modelAndView = new ModelAndView("pdfBHNTrtCompReport", parameterMap);
        }
        if ("BHN_PROG_COMP".equals(repName)) {
        	programCompRpt = reportJDBCTemplate.generateProgramCompRpt(2L, startDate, endDate);
        	JRdataSource = new JRBeanCollectionDataSource(programCompRpt);
            parameterMap.put("rptdatasource", JRdataSource);
            if ("PDF".equals(repFmt))
            	modelAndView = new ModelAndView("pdfBHNProgCompReport", parameterMap);
        }
        if ("RSAT_CLIENT_LIST".equals(repName)) {
        	clientList = reportJDBCTemplate.listRsatClients(2L, startDate, endDate, orderBy);
        	JRdataSource = new JRBeanCollectionDataSource(clientList);
            parameterMap.put("rptdatasource", JRdataSource);
            if ("PDF".equals(repFmt))
            	modelAndView = new ModelAndView("pdfRSATClientReport", parameterMap);
            if ("XLS".equals(repFmt))
            	modelAndView = new ModelAndView("xlsRSATClientReport", parameterMap);
        }
        if ("BHN_RSAT".equals(repName)) {
        	rsatRpt = reportJDBCTemplate.generateRsatRpt(2L, startDate, endDate, evidence, evidenceTreatment, noOfDirectStaff,
        			noOfMentalHealthFacility, noOfSunstanceAbuseFacility, noOfPrimaryCareFacility);
        	JRdataSource = new JRBeanCollectionDataSource(rsatRpt);
            parameterMap.put("rptdatasource", JRdataSource);
            if ("PDF".equals(repFmt))
            	modelAndView = new ModelAndView("pdfBHNRsatReport", parameterMap);
        }

        return modelAndView;
	}
	
	@RequestMapping(value = "rptIntake", method = RequestMethod.GET) 
	public ModelAndView rptIntake() { 
		return new ModelAndView("rptintake", "command", new Client()); 
	}

}
