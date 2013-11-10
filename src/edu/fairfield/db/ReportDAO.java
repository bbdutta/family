package edu.fairfield.db;

import java.util.List;

import javax.sql.DataSource;

import edu.fairfield.Client;
import edu.fairfield.DischargePlanRpt;
import edu.fairfield.ProgramCompRpt;
import edu.fairfield.SubstanceFreeRpt;
import edu.fairfield.TreatmentCompRpt;

public interface ReportDAO {

	/** 
	 * This is the method to be used to initialize 
	 * database resources ie. connection.
	 */ 
	public void setDataSource(DataSource ds); 
	
	/** 
	 * This is the method to be used to list down 
	 * a record from the Client table corresponding 
	 * to a passed client id. 
	 * 
	 */ 
	public Client getClient(Long id); 
	
	/** 
	 * This is the method to be used to list down 
	 * all the records from the Client table. 
	 */
	public List<Client> listClients(long programId, String startDate, String endDate, String orderBy); 
	
	public List<SubstanceFreeRpt> generateSubFreeRpt(long programId, String startDate, String endDate);
	public List<DischargePlanRpt> generateDischargePlanRpt(long programId, String startDate, String endDate);
	public List<TreatmentCompRpt> generateTreatmentCompRpt(long programId, String startDate, String endDate);
	public List<ProgramCompRpt> generateProgramCompRpt(long programId, String startDate, String endDate);
}

