package edu.fairfield.db;

import java.util.Calendar;
import java.util.List; 

import javax.sql.DataSource;

import edu.fairfield.Client;
import edu.fairfield.Rsat;

public interface ClientDAO {

	/** 
	 * This is the method to be used to initialize 
	 * database resources ie. connection.
	 */ 
	public void setDataSource(DataSource ds); 
	
	/** 
	 * This is the method to be used to create 
	 * a record in the Client table. 
	 */ 
	public void create(long inmateNum, String firstName, String middleName, String lastName, String gender, Calendar dob, String educationLevel, 
			Calendar doa, String race, String referralSource, String tanfEligible, long programId); 

	/** 
	 * This is the method to be used to add discharge information 
	 * a record in the Client_program table. 
	 */ 
	public void addDischarge(Long clientId, Long programId, Calendar dischargeDate, int lengthofStay, String attdallsppts, int numdrugtests, String monthlydrugtest, int numpositve, 
			String dischargePlan, String commLinkages, String educationLevel, String housing, String dischargeReason, String dismissalReason, String trtModality, String medicationAssisted, String validatedTrt); 

	/** 
	 * This is the method to be used to list down 
	 * a record from the Client table corresponding 
	 * to a passed client id. 
	 * 
	 */ 
	public Client getClient(long clientId, long programId); 
	
	/** 
	 * This is the method to be used to list down 
	 * all the records from the Client table. 
	 */
	public List<Client> listClients(Long programId); 
	
	/** 
	 * This is the method to be used to delete 
	 * a record from the Client table corresponding 
	 * to a passed client id. 
	 */ 
	public void delete(Long id); 
	
	/** 
	 * This is the method to be used to update 
	 * a record into the Client table. 
	 */ 
	public void update(long inmateNum, String firstName, String middleName, String lastName, String gender, Calendar dob, String educationLevel, Calendar doa, 
			String race, String referralSource, String tanfEligible, long clientId, long programId);

	public Rsat getRsat(long inmateNum, long programId);
	
	public void addRsat(Long clientId, Long programId, Calendar progAddDate, Calendar orientationDate, String orientationFacility, String recRiskAsmt, Calendar asmtDate, String toolName, String highCrimeogenicRisk,
			String compIndTrtPlan, String enrollRsatAftercare, Calendar aftercareEnrollDate, String contCareAgmt, Calendar serviceDate, String serviceType,    
			String otherService, String compAllAftercareReq, Calendar compDate, String reasonNonComp, String otherReason, 
			Calendar drugTestDate, String testedPositive, int noOfUrineTest, String agenciesAssistedClient, 
			String healthCareProvider, String enrolledMedicaid, Calendar progCompDate); 


}
