package edu.fairfield.db;

import java.util.List;

import javax.sql.DataSource;

import edu.fairfield.Employment;
import edu.fairfield.Goal;
import edu.fairfield.GoalService;

public interface CaseDAO {

	/** 
	 * This is the method to be used to initialize 
	 * database resources ie. connection.
	 */ 
	public void setDataSource(DataSource ds); 
	
	/** 
	 * This is the method to be used to create 
	 * a record in the Client table. 
	 */ 
	public void create(Employment employment); 
	
	public List<Goal> getGoals();
	
	public List<GoalService> getGoalServices();
	
	public Employment getEmployment(long clientId, long programId); 
	
	List<GoalService> getSelectedGoalServices(long employmentId);
	
}