package edu.fairfield.db;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import edu.fairfield.Client;
import edu.fairfield.DischargePlanRpt;
import edu.fairfield.ProgramCompRpt;
import edu.fairfield.SubstanceFreeRpt;
import edu.fairfield.TreatmentCompRpt;

public class ReportJDBCTemplate implements ReportDAO {

	private DataSource dataSource; 
	private JdbcTemplate jdbcTemplateObject;
	
	@Override
	public void setDataSource(DataSource ds) {
		
		this.dataSource = ds;
		this.jdbcTemplateObject = new JdbcTemplate(ds);
	}

	@Override
	public Client getClient(Long id) {
		String SQL = "SELECT * from Client where client_id = " + id; 
		return jdbcTemplateObject.queryForObject(SQL, new ClientMapper());  
	}

	@Override
	public List<Client> listClients(long programId, String startDate, String endDate) {
		String SQL = " SELECT c.*, p.* " +
				" FROM client c, client_program p " +
				" where c.client_id = p.client_id " +
				" and p.date_admitted >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') " +
				" and (p.date_discharged <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') or p.date_discharged is null) " +
				" and p.program_id = " + programId; 
		return jdbcTemplateObject.query(SQL, new ClientMapper());
	}

	@Override
	public List<SubstanceFreeRpt> generateSubFreeRpt(long programId,
			String startDate, String endDate) {
		SubstanceFreeRpt rpt = new SubstanceFreeRpt();
		String SQL = " select count(*) clients_tested_once_per_month " +
					" from client_program " +
					" where drug_test_monthly = 'Y' " +
					" and date_admitted >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') " +
					" and date_discharged <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') " +
					" and program_id = " + programId; 
		int testedOncePerMonth = jdbcTemplateObject.queryForInt(SQL);
		
		SQL = " select sum(num_drug_test) total_drug_test " +
				" from client_program " +
				" where date_admitted >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') " +
				" and date_discharged <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') " +
				" and program_id = " + programId; 

		int totalDrugTest = jdbcTemplateObject.queryForInt(SQL);
		
		SQL = " select count(*) num_tested_positive " +
				" from client_program " +
				" where num_pos_durg_test >= 1 " +
				" and date_admitted >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') " +
				" and date_discharged <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') " +
				" and program_id = " + programId; 

		int totalPosDrugTest = jdbcTemplateObject.queryForInt(SQL);
		
		List<SubstanceFreeRpt> rptList = new ArrayList<SubstanceFreeRpt>();
		rpt.setTestedOncePerMonthNum(testedOncePerMonth);
		rpt.setTestedPositiveNum(totalPosDrugTest);
		rpt.setTotalDrugTestNum(totalDrugTest);
		rpt.setTestedPositivePct();
		rpt.setTestedOncePerMonthPct();
		rpt.setStartDate(startDate);
		rpt.setEndDate(endDate);
		rptList.add(rpt);
		
		return rptList;
	}

	@Override
	public List<DischargePlanRpt> generateDischargePlanRpt(long programId,
			String startDate, String endDate) {
		DischargePlanRpt rpt = new DischargePlanRpt();
		String SQL = " SELECT count(*) idp_cnt " +
					" FROM client_program " +
					" where discharge_plan = 'Y'" +
					" and date_admitted >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') " +
					" and date_discharged <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') " +
					" and program_id = " + programId; 
		int idpNum = jdbcTemplateObject.queryForInt(SQL);
		
		SQL = " SELECT count(*) comm_link_cnt " +
				" FROM client_program " +
				" where community_linkages = 'Y' " +
				" and date_admitted >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') " +
				" and date_discharged <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') " +
				" and program_id = " + programId; 

		int preLinkComNum = jdbcTemplateObject.queryForInt(SQL);
		
		SQL = " SELECT count(*) less_30_noplan_cnt " +
				" FROM client_program " +
				" where discharge_plan = 'N' " +
				" and date_admitted > (now() - interval 30 day) " +
				" and program_id = " + programId; 

		int less30NoPlanNum = jdbcTemplateObject.queryForInt(SQL);
		
		List<DischargePlanRpt> rptList = new ArrayList<DischargePlanRpt>();
		rpt.setIdpNum(idpNum);
		rpt.setPreLinkComNum(preLinkComNum);
		rpt.setLess30NoPlanNum(less30NoPlanNum);
		rpt.setStartDate(startDate);
		rpt.setEndDate(endDate);
		rptList.add(rpt);
		
		return rptList;
	}
	
	@Override
	public List<TreatmentCompRpt> generateTreatmentCompRpt(long programId,
			String startDate, String endDate) {
		TreatmentCompRpt rpt = new TreatmentCompRpt();
		String SQL = " SELECT count(*) attend_appt_cnt " +
					" FROM client_program " +
					" where attended_all_appts = 'Y'" +
					" and date_admitted >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') " +
					" and date_discharged <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') " +
					" and program_id = " + programId; 
		int attendedAllAppNum = jdbcTemplateObject.queryForInt(SQL);
		
		SQL = " SELECT count(*) missed_appt_cnt " +
				" FROM client_program " +
				" where attended_all_appts = 'N'" +
				" and date_admitted >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') " +
				" and date_discharged <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') " +
				" and program_id = " + programId; 

		int missedAppNum = jdbcTemplateObject.queryForInt(SQL);
		
		SQL = " SELECT count(*) still_assess_cnt " +
				" FROM client_program " +
				" where attended_all_appts is null " +
				" and date_admitted >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') " +
				" and date_discharged <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') " +
				" and program_id = " + programId; 

		int stillInAssessmentNum = jdbcTemplateObject.queryForInt(SQL);
		
		List<TreatmentCompRpt> rptList = new ArrayList<TreatmentCompRpt>();
		rpt.setAttendedAllAppNum(attendedAllAppNum);
		rpt.setMissedAppNum(missedAppNum);
		rpt.setStillInAssessmentNum(stillInAssessmentNum);
		rpt.setStartDate(startDate);
		rpt.setEndDate(endDate);
		rptList.add(rpt);
		
		return rptList;
	}

	@Override
	public List<ProgramCompRpt> generateProgramCompRpt(long programId,
			String startDate, String endDate) {
		ProgramCompRpt rpt = new ProgramCompRpt();
		String SQL = " select count(*) successful_discharge " +
					" from client_program " +
					" where discharge_reason = 'Program completion' " +
					" and date_admitted >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') " +
					" and date_discharged <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') " +
					" and program_id = " + programId; 
		int successDischargeNum = jdbcTemplateObject.queryForInt(SQL);
		
		SQL = " select count(*) unsuccessful_discharge " +
				" from client_program " +
				" where discharge_reason = 'Dismissal' " +
				" and date_admitted >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') " +
				" and date_discharged <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') " +
				" and program_id = " + programId; 

		int unsuccessDischargeNum = jdbcTemplateObject.queryForInt(SQL);
		
		SQL = " select count(*) unsuccessful_discharge " +
				" from client_program " +
				" where discharge_reason is not null " +
				" and discharge_reason != 'Program completion'" +
				" and date_admitted >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') " +
				" and date_discharged <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') " +
				" and program_id = " + programId; 

		int otherDischargeNum = jdbcTemplateObject.queryForInt(SQL);
		
		List<ProgramCompRpt> rptList = new ArrayList<ProgramCompRpt>();
		rpt.setSuccessDischargeNum(successDischargeNum);
		rpt.setUnsuccessDischargeNum(unsuccessDischargeNum);
		rpt.setOtherDischargeNum(otherDischargeNum);
		rpt.setSuccessDischargePct();
		rpt.setStartDate(startDate);
		rpt.setEndDate(endDate);
		rptList.add(rpt);
		
		return rptList;
	}

}
