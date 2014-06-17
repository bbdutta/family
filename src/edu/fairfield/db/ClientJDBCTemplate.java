package edu.fairfield.db;

import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

import edu.fairfield.Client;
import edu.fairfield.Rsat;

public class ClientJDBCTemplate implements ClientDAO {

	private DataSource dataSource; 
	private JdbcTemplate jdbcTemplateObject;
	
	private static final Logger logger = Logger.getLogger(ClientJDBCTemplate.class);
			
	@Override
	public void setDataSource(DataSource ds) {
		
		this.dataSource = ds;
		this.jdbcTemplateObject = new JdbcTemplate(ds);
	}

	@Override
	public void create(long inmateNum, String firstName, String middleName,
			String lastName, String gender, Calendar dob, String educationLevel, Calendar doa, String race, String referralSource, String tanfEligible, long programId) {
					
		String SQL = "insert into client (inmate_number, first_name, middle_name, last_name, gender, dob, educational_level, race) " +
				"values (?, ?, ?, ?, ?, ?, ?, ?)"; 
		try {
			jdbcTemplateObject.update( SQL, inmateNum, firstName, middleName, lastName, gender, dob, educationLevel, race);
			logger.info("ClientJDBCTemplate::create: inmateNum -> "+inmateNum+" : firstName -> "+firstName+" : middleName -> "+middleName+" : lastName -> "+
					lastName+" : gender -> "+gender+" : dob -> "+dob+" : educationLevel -> "+educationLevel+" : race -> "+race);
		} catch (DuplicateKeyException d) {d.printStackTrace();}
		SQL = "select client_id from client where inmate_number = ?"; 
		long clientId =  jdbcTemplateObject.queryForLong(SQL,new Object[] { inmateNum });
		SQL = "insert into client_program (client_id, program_id, date_admitted, referral_source, tanf_eligible) values (?, ?, ?, ?, ?)";
		jdbcTemplateObject.update( SQL, clientId, programId, doa, referralSource, tanfEligible);
		logger.info("ClientJDBCTemplate::create: doa -> "+doa+" : referralSource -> "+referralSource+" : tanfEligible -> "+tanfEligible+" : clientId -> "+
				clientId+" : programId -> "+programId);
		SQL = "insert into client_employment (client_id, program_id) values (?, ?)";
		jdbcTemplateObject.update( SQL, clientId, programId);
		SQL = "insert into bhn_rsat (client_id, program_id) values (?, ?)";
		jdbcTemplateObject.update( SQL, clientId, programId);
		System.out.println("Created Record Name=" + firstName + " " + lastName); 
	}

	@Override
	public void addDischarge(Long clientId, Long programId, Calendar dischargeDate, int lengthofStay, String attdallsppts, int numdrugtests, 
			String monthlydrugtest, int numpositve, String dischargePlan, String commLinkages, String educationLevel, 
			String housing, String dischargeReason, String dismissalReason, String trtModality, String medicationAssisted, String validatedTrt) {
		String SQL = "UPDATE client_program set date_discharged = ?,length_of_stay = ?,attended_all_appts = ?,num_drug_test =?," +
				"drug_test_monthly = ?,num_pos_durg_test = ?,discharge_plan = ?,community_linkages = ?,educational_level = ?,client_housing = ?," +
				"discharge_reason = ?,dismissal_reason = ?,treatment_modality = ?,medication_assisted = ?,validated_treatment = ? where client_id = ? and program_id = ?"; 
		jdbcTemplateObject.update(SQL, dischargeDate, lengthofStay, attdallsppts, numdrugtests, 
				monthlydrugtest, numpositve, dischargePlan, commLinkages, educationLevel, 
				housing, dischargeReason, dismissalReason, trtModality, medicationAssisted, validatedTrt, clientId, programId); 
	}
	
	@Override
	public Client getClient(long inmateNum, long programId) {
		String SQL = "SELECT c.*, p.* from client c, client_program p where c.inmate_number = " + inmateNum + " and c.client_id = p.client_id and p.program_id = " + programId; 
		return jdbcTemplateObject.queryForObject(SQL, new ClientMapper());  
	}

	@Override
	public List<Client> listClients(Long programId) {
		String SQL = "SELECT c.*, p.* FROM client c, client_program p where c.client_id = p.client_id and p.program_id = "+programId; 
		return jdbcTemplateObject.query(SQL, new ClientMapper());
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long inmateNum, String firstName, String middleName, String lastName, String gender, Calendar dob, String educationLevel, Calendar doa, 
			String race, String referralSource, String tanfEligible, long clientId, long programId) {
		String SQL = "update client set inmate_number=?, first_name=?, middle_name=?, last_name=?, gender=?, dob=?, educational_level=?, race=? " +
				" where client_id=?"; 
		jdbcTemplateObject.update( SQL, inmateNum, firstName, middleName, lastName, gender, dob, educationLevel, race, clientId);
		logger.info("ClientJDBCTemplate::update: inmateNum -> "+inmateNum+" : firstName -> "+firstName+" : middleName -> "+middleName+" : lastName -> "+
		lastName+" : gender -> "+gender+" : dob -> "+dob+" : educationLevel -> "+educationLevel+" : race -> "+race+" : clientId-> "+clientId);
		SQL = "update client_program set date_admitted=?, referral_source=?, tanf_eligible=? " +
				" where client_id=? and program_id=?"; 
		jdbcTemplateObject.update( SQL, doa, referralSource, tanfEligible, clientId, programId);
		logger.info("ClientJDBCTemplate::update: doa -> "+doa+" : referralSource -> "+referralSource+" : tanfEligible -> "+tanfEligible+" : clientId -> "+
				clientId+" : programId -> "+programId);
		System.out.println("Updated Record Name=" + firstName + " " + lastName); 
	}

	@Override
	public Rsat getRsat(long inmateNum, long programId) {
		String SQL = "SELECT r.* from client c, bhn_rsat r where c.inmate_number = " + 
				inmateNum + " and c.client_id = r.client_id and r.program_id = " + programId; 
		return jdbcTemplateObject.queryForObject(SQL, new RsatMapper());  
	}

	@Override
	public void addRsat(Long clientId, Long programId, String recRiskAsmt, Calendar asmtDate, String toolName, String highCrimeogenicRisk,
			String compIndTrtPlan, String enrollRsatAftercare, Calendar aftercareEnrollDate, String contCareAgmt, Calendar serviceDate, String serviceType,    
			String otherService, String compAllAftercareReq, Calendar compDate, String reasonNonComp, String otherReason, 
			Calendar drugTestDate, String testedPositive, String healthCareProvider, String enrolledMedicaid) {
		String SQL = "UPDATE bhn_rsat set received_risk_asmt = ?,assmt_date = ?,toolname_used = ?,high_crimeogenic_risk =?,"
				+ "completed_ind_trt_plan = ?,enrolled_rsat_aftercare = ?,aftercare_enroll_date = ?,cont_care_agmt = ?,service_date = ?,"
				+ "service_type = ?, other_service = ?,comp_all_aftercare_req = ?,completion_date = ?,reason_non_completion = ?,"
				+ "other_reason = ?, drug_test_date = ?, tested_positive_sub = ?, health_care_provider = ?, enrolled_medicaid = ?"
				+ " where client_id = ? and program_id = ?"; 
		jdbcTemplateObject.update(SQL, recRiskAsmt, asmtDate, toolName, highCrimeogenicRisk, compIndTrtPlan, enrollRsatAftercare, 
				aftercareEnrollDate, contCareAgmt, serviceDate, serviceType, otherService, compAllAftercareReq, compDate, reasonNonComp, otherReason, 
				drugTestDate, testedPositive, healthCareProvider, enrolledMedicaid, clientId, programId); 
	}

}
