package edu.fairfield.db;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import edu.fairfield.Client;
import edu.fairfield.DischargePlanRpt;
import edu.fairfield.ProgramCompRpt;
import edu.fairfield.RsatRpt;
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
	public List<Client> listClients(long programId, String startDate, String endDate, String orderBy) {
		String SQL = " SELECT c.*, p.* " +
				" FROM client c, client_program p " +
				" where c.client_id = p.client_id " +
				" and p.date_admitted >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') " +
				" and (p.date_discharged <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') " +
				" or p.date_discharged is null) " +
				" and p.referral_source != 'RSAT' " +
				" and p.program_id = " + programId; 
		if ("FIRST".equals(orderBy))
			SQL = SQL + " order by c.first_name";
		if ("LAST".equals(orderBy))
			SQL = SQL + " order by c.last_name";
		
		return jdbcTemplateObject.query(SQL, new ClientMapper());
	}

	@Override
	public List<Client> listRsatClients(long programId, String startDate, String endDate, String orderBy) {
		String SQL = " SELECT c.*, p.* " +
				" FROM client c, client_program p " +
				" where c.client_id = p.client_id " +
				" and p.date_admitted >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') " +
				" and (p.date_discharged <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') " +
				" or p.date_discharged is null) " +
				" and p.referral_source = 'RSAT' " +
				" and p.program_id = " + programId; 
		if ("FIRST".equals(orderBy))
			SQL = SQL + " order by c.first_name";
		if ("LAST".equals(orderBy))
			SQL = SQL + " order by c.last_name";
		
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

	@Override
	public List<RsatRpt> generateRsatRpt(long programId, String startDate, String endDate,
			String evidence, String evidenceTreatment, String noOfDirectStaff, long noOfMentalHealthFacility, 
			long noOfSunstanceAbuseFacility, long noOfPrimaryCareFacility) {
		RsatRpt rpt = new RsatRpt();
		List<RsatRpt> rptList = new ArrayList<RsatRpt>();
		rptList.add(rpt);
		rpt.setEviBasedTrt(evidence);
		rpt.setEviBasedDesc(evidenceTreatment);
		rpt.setNoOfDirectStaff(Long.parseLong(noOfDirectStaff));
		rpt.setNoOfMentalHealthProv(noOfMentalHealthFacility);
		rpt.setNoOfSubAbuseProv(noOfSunstanceAbuseFacility);
		rpt.setNoOfPriCareProv(noOfPrimaryCareFacility);
		rpt.setStartDate(startDate);
		rpt.setEndDate(endDate);


		String SQL = " SELECT count(*) high_crimeogenic_risk_cnt "
				+ " FROM bhn_rsat "
				+ " where high_crimeogenic_risk = 'Y' "
				+ " and admission_date >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') "
				+ " and admission_date <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') "
				+ " and program_id = " + programId;
				
		int highCrimeogenicRiskCnt = jdbcTemplateObject.queryForInt(SQL);
		rpt.setNoOfHighTrtNeed(highCrimeogenicRiskCnt);
		
		SQL = " SELECT count(*) comp_ind_trt_plan_cnt "
				+ " FROM bhn_rsat "
				+ " where completed_ind_trt_plan = 'Y' "
				+ " and admission_date >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') "
				+ " and admission_date <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') "
				+ " and program_id = " + programId;

		int compIndTrtPlanCnt = jdbcTemplateObject.queryForInt(SQL);
		rpt.setNoOfCompIndTrtPlan(compIndTrtPlanCnt);;
		
		SQL = " SELECT count(*) enrolled_rsat_aftercare_cnt "
				+ " FROM bhn_rsat where "
				+ " admission_date < STR_TO_DATE('" + endDate + "','%Y-%m-%d')"
				+ " and (completion_date is null or completion_date > STR_TO_DATE('" + endDate + "','%Y-%m-%d')) "
				+ "	and program_id = " + programId; 

		int enrolledAftercareCnt = jdbcTemplateObject.queryForInt(SQL);
		rpt.setNoOfRsatAftercareEn(enrolledAftercareCnt);
		
		SQL = " SELECT count(*) enrolled_rsat_aftercare_cnt "
				+ " FROM bhn_rsat where "
				+ " admission_date >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') "
				+ " and admission_date < STR_TO_DATE('" + endDate + "','%Y-%m-%d') "
				+ " and (completion_date is null or completion_date > STR_TO_DATE('" + endDate + "','%Y-%m-%d')) "
				+ "	and program_id = " + programId; 

		int newAftercareCnt = jdbcTemplateObject.queryForInt(SQL);
		rpt.setNoOfNewAftercareEn(newAftercareCnt);

		SQL = " SELECT count(*) cont_care_agmt_cnt "
				+ " FROM bhn_rsat "
				+ " where cont_care_agmt = 'Y' "
				+ " and admission_date >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') "
				+ " and admission_date <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') "
				+ " and program_id = " + programId;

		int contCareAgmtCnt = jdbcTemplateObject.queryForInt(SQL);
		rpt.setNoOfNewTransPlan(contCareAgmtCnt);

		SQL = " SELECT count(*) service_cnt "
				+ " FROM bhn_rsat "
				+ " where "
				+ " service_date >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') "
				+ " and service_date <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') "
				+ " and program_id = " + programId;

		int servicedCnt = jdbcTemplateObject.queryForInt(SQL);
		rpt.setNoOfServiced(servicedCnt);

		SQL = " SELECT count(*) comp_all_aftercare_req_cnt "
				+ " FROM bhn_rsat "
				+ " where comp_all_aftercare_req = 'Y' "
				+ " and admission_date >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') "
				+ " and admission_date <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') "
				+ " and program_id = " + programId;

		int compAllAftercareReqCnt = jdbcTemplateObject.queryForInt(SQL);
		rpt.setNoOfSuccessComp(compAllAftercareReqCnt);
		
		SQL = " SELECT count(*) not_comp_all_aftercare_req_cnt "
				+ " FROM bhn_rsat "
				+ " where comp_all_aftercare_req = 'N' "
				+ " and admission_date >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') "
				+ " and admission_date <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') "
				+ " and program_id = " + programId;

		int notCompAllAftercareReqCnt = jdbcTemplateObject.queryForInt(SQL);
		rpt.setNoOfNonComp(notCompAllAftercareReqCnt);

		SQL = " SELECT count(*) comp_all_aftercare_req_3_mon_cnt "
				+ " FROM bhn_rsat "
				+ " where comp_all_aftercare_req = 'Y' "
				+ " and completion_date <= admission_date + interval 3 month "
				+ " and admission_date >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') "
				+ " and admission_date <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') "
				+ " and program_id = " + programId;

		int compAllAftercareReq3monCnt = jdbcTemplateObject.queryForInt(SQL);
		rpt.setNoOfComp3m(compAllAftercareReq3monCnt);

		SQL = " SELECT count(*) comp_all_aftercare_req_4_6_mon_cnt "
				+ " FROM bhn_rsat "
				+ " where comp_all_aftercare_req = 'Y' "
				+ " and completion_date > admission_date + interval 3 month "
				+ " and completion_date <= admission_date + interval 6 month "
				+ " and admission_date >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') "
				+ " and admission_date <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') "
				+ " and program_id = " + programId;

		int compAllAftercareReq4to6monCnt = jdbcTemplateObject.queryForInt(SQL);
		rpt.setNoOfComp6m(compAllAftercareReq4to6monCnt);

		SQL = " SELECT count(*) comp_all_aftercare_req_7_9_mon_cnt "
				+ " FROM bhn_rsat "
				+ " where comp_all_aftercare_req = 'Y' "
				+ " and completion_date > admission_date + interval 6 month "
				+ " and completion_date <= admission_date + interval 9 month "
				+ " and admission_date >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') "
				+ " and admission_date <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') "
				+ " and program_id = " + programId;

		int compAllAftercareReq7to9monCnt = jdbcTemplateObject.queryForInt(SQL);
		rpt.setNoOfComp9m(compAllAftercareReq7to9monCnt);

		SQL = " SELECT count(*) comp_all_aftercare_req_10_mon_cnt "
				+ " FROM bhn_rsat "
				+ " where comp_all_aftercare_req = 'Y' "
				+ " and completion_date > admission_date + interval 9 month "
				+ " and admission_date >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') "
				+ " and admission_date <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') "
				+ " and program_id = " + programId;

		int compAllAftercareReq10monCnt = jdbcTemplateObject.queryForInt(SQL);
		rpt.setNoOfComp10m(compAllAftercareReq10monCnt);

		SQL = " SELECT count(*) not_comp_all_aftercare_req_3_mon_cnt "
				+ " FROM bhn_rsat "
				+ " where comp_all_aftercare_req = 'N' "
				+ " and completion_date <= admission_date + interval 3 month "
				+ " and admission_date >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') "
				+ " and admission_date <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') "
				+ " and program_id = " + programId;

		int notCompAllAftercareReq3monCnt = jdbcTemplateObject.queryForInt(SQL);
		rpt.setNoOfNonComp3m(notCompAllAftercareReq3monCnt);

		SQL = " SELECT count(*) not_comp_all_aftercare_req_4_6_mon_cnt "
				+ " FROM bhn_rsat "
				+ " where comp_all_aftercare_req = 'N' "
				+ " and completion_date > admission_date + interval 3 month "
				+ " and completion_date <= admission_date + interval 6 month "
				+ " and admission_date >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') "
				+ " and admission_date <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') "
				+ " and program_id = " + programId;

		int notCompAllAftercareReq4to6monCnt = jdbcTemplateObject.queryForInt(SQL);
		rpt.setNoOfNonComp6m(notCompAllAftercareReq4to6monCnt);

		SQL = " SELECT count(*) not_comp_all_aftercare_req_7_9_mon_cnt "
				+ " FROM bhn_rsat "
				+ " where comp_all_aftercare_req = 'N' "
				+ " and completion_date > admission_date + interval 6 month "
				+ " and completion_date <= admission_date + interval 9 month "
				+ " and admission_date >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') "
				+ " and admission_date <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') "
				+ " and program_id = " + programId;

		int notCompAllAftercareReq7to9monCnt = jdbcTemplateObject.queryForInt(SQL);
		rpt.setNoOfNonComp3m(notCompAllAftercareReq7to9monCnt);

		SQL = " SELECT count(*) not_comp_all_aftercare_req_10_mon_cnt "
				+ " FROM bhn_rsat "
				+ " where comp_all_aftercare_req = 'N' "
				+ " and completion_date > admission_date + interval 9 month "
				+ " and admission_date >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') "
				+ " and admission_date <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') "
				+ " and program_id = " + programId;

		int notCompAllAftercareReq10monCnt = jdbcTemplateObject.queryForInt(SQL);
		rpt.setNoOfNonComp3m(notCompAllAftercareReq10monCnt);

		SQL = " SELECT count(*) drug_test_before_admission_cnt "
				+ " FROM bhn_rsat "
				+ " where enrolled_rsat_aftercare = 'Y' "
				+ " and drug_test_date < admission_date "
				+ " and admission_date >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') "
				+ " and admission_date <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') "
				+ " and program_id = " + programId;

		int drugTestBeforeAdmissionCnt = jdbcTemplateObject.queryForInt(SQL);
		rpt.setNoOfPartiTestBeforeAdm(drugTestBeforeAdmissionCnt);

		SQL = " SELECT count(*) enrolled_aftercare_drug_test_cnt "
				+ " FROM bhn_rsat "
				+ " where enrolled_rsat_aftercare = 'Y' "
				+ " and drug_test_date is not null "
				+ " and admission_date >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') "
				+ " and admission_date <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') "
				+ " and program_id = " + programId;

		int enrolledAftercareDrugTestCnt = jdbcTemplateObject.queryForInt(SQL);
		rpt.setNoOfPartiTestDuringRpt(enrolledAftercareDrugTestCnt);

		SQL = " SELECT count(*) drug_test_after_comp_cnt "
				+ " FROM bhn_rsat "
				+ " where enrolled_rsat_aftercare = 'Y' "
				+ " and drug_test_date > completion_date "
				+ " and admission_date >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') "
				+ " and admission_date <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') "
				+ " and program_id = " + programId;

		int drugTestAfterCompCnt = jdbcTemplateObject.queryForInt(SQL);
		rpt.setNoOfPartiTestAfterComp(drugTestAfterCompCnt);

		SQL = " SELECT count(*) test_pos_after_comp_cnt "
				+ " FROM bhn_rsat "
				+ " where enrolled_rsat_aftercare = 'Y' "
				+ " and tested_positive_sub = 'Y' "
				+ " and drug_test_date > completion_date "
				+ " and admission_date >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') "
				+ " and admission_date <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') "
				+ " and program_id = " + programId;

		int testedPosAfterCompCnt = jdbcTemplateObject.queryForInt(SQL);
		rpt.setNoOfPartiTestPosAfterComp(testedPosAfterCompCnt);

		SQL = " SELECT count(*) health_coverage_cnt "
				+ " FROM bhn_rsat "
				+ " where health_care_provider is not null "
				+ " and admission_date >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') "
				+ " and admission_date <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') "
				+ " and program_id = " + programId;

		int healthCoverageCnt = jdbcTemplateObject.queryForInt(SQL);
		rpt.setNoOfPartiHealthCover(healthCoverageCnt);
		
		SQL = " SELECT count(*) medicaid_cnt "
				+ " FROM bhn_rsat "
				+ " where enrolled_medicaid  = 'Y' "
				+ " and admission_date >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') "
				+ " and admission_date <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') "
				+ " and program_id = " + programId;

		int medicaidCnt = jdbcTemplateObject.queryForInt(SQL);
		rpt.setNoOfPartiMedicaid(medicaidCnt);

		SQL = " SELECT count(*) new_health_coverage_cnt "
				+ " FROM bhn_rsat "
				+ " where health_care_provider is not null "
				+ " and aftercare_enroll_date >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') "
				+ " and aftercare_enroll_date <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') "
				+ " and admission_date >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') "
				+ " and admission_date <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') "
				+ " and program_id = " + programId;

		int newHealthCoverageCnt = jdbcTemplateObject.queryForInt(SQL);
		rpt.setNoOfNewPartiHealthCover(newHealthCoverageCnt);
		
		SQL = " SELECT count(*) new_medicaid_cnt "
				+ " FROM bhn_rsat "
				+ " where enrolled_medicaid  = 'Y' "
				+ " and aftercare_enroll_date >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') "
				+ " and aftercare_enroll_date <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') "
				+ " and admission_date >= STR_TO_DATE('" + startDate + "','%Y-%m-%d') "
				+ " and admission_date <= STR_TO_DATE('" + endDate + "','%Y-%m-%d') "
				+ " and program_id = " + programId;

		int newMedicaidCnt = jdbcTemplateObject.queryForInt(SQL);
		rpt.setNoOfNewPartiMedicaid(newMedicaidCnt);
		
		
		return rptList;
	}

}
