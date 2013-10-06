package edu.fairfield.db;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import edu.fairfield.Employment;
import edu.fairfield.Goal;
import edu.fairfield.GoalService;

public class CaseJDBCTemplate implements CaseDAO {

	private DataSource dataSource; 
	private JdbcTemplate jdbcTemplateObject;
	
	@Override
	public void setDataSource(DataSource ds) {
		
		this.dataSource = ds;
		this.jdbcTemplateObject = new JdbcTemplate(ds);
	}

	@Override
	public void create(Employment employment) {

		String SQL = "UPDATE client_employment set needed_benefits=?,applied_benefits=?,received_benefits=?,needed_id=?,applied_id=?, " +
				" received_id=?,employer_name=?,is_employed=?,date_of_hire=?,is_fulltime=?,is_parttime=?,hourly_rate=?,job_retention_30=?, " +
				" job_retention_60=?,job_retention_90=?,job_retention_90_plus=?,social_reunification=?,enrolled_job_readiness=?,completed_job_rediness=? "+
				" where client_id=? and program_id=?";

		jdbcTemplateObject.update( SQL,employment.getNeededBenefits(),employment.getAppliedBenefits(),employment.getReceivedBenefits(),employment.getNeededId(),
				employment.getAppliedId(),employment.getReceivedId(),employment.getEmployerName(),employment.getIsEmployed(),employment.getDateOfHire(),employment.getIsFulltime(),
				employment.getIsParttime(),employment.getHourlyRate(),employment.getJobRetention30(),employment.getJobRetention60(),employment.getJobRetention90(),
				employment.getJobRetention90Plus(),employment.getSocialReunification(),employment.getEnrolledJobReadiness(),employment.getCompletedJobRediness(),
				employment.getClientId(),employment.getProgramId()); 
	
		if(employment.getSelectedGoalService() != null) {
			String gsSeqSQL = "select max(client_employment_goal_service_id) from client_goal_service";
			Integer seq = jdbcTemplateObject.queryForInt(gsSeqSQL);
			String gsEmpSQL = "select max(client_employment_id) from client_employment";
			Integer emp = jdbcTemplateObject.queryForInt(gsEmpSQL);
			for(GoalService gs:employment.getSelectedGoalService()) {
				String gsSQL = "INSERT INTO client_goal_service (`client_employment_goal_service_id`, `client_employment_id`, `goal_service_id`) VALUES (?, ?, ?);";
				seq++;
				jdbcTemplateObject.update(gsSQL, seq, emp, gs.getGoalServiceId());
			}
		}

	}
	
	@Override
	public List<Goal> getGoals() {
		String SQL = "select goal_id, goal_name from goal"; 
		return jdbcTemplateObject.query(SQL, new GoalMapper());  
	}

	@Override
	public List<GoalService> getGoalServices() {
		String SQL = "select goal_service_id, goal.goal_id as goal_id, goal_name, service.service_id as service_id, service_name from goal, service, goal_service where goal.goal_id=goal_service.goal_id and service.service_id=goal_service.service_id"; 
		return jdbcTemplateObject.query(SQL, new GoalServiceMapper()); 
	}

	@Override
	public Employment getEmployment(long clientId, long programId) {
		String SQL = "SELECT c.*, e.* from client c, client_employment e where c.client_id = " + clientId + " and c.client_id = e.client_id and e.program_id = " + programId; 
		return jdbcTemplateObject.queryForObject(SQL, new EmploymentMapper());  
	}

	@Override
	public List<GoalService> getSelectedGoalServices(long employmentId) {
		String SQL = "select goal_service.goal_service_id, goal.goal_id as goal_id, goal_name, service.service_id as service_id, service_name from goal, service, goal_service, client_goal_service where goal.goal_id=goal_service.goal_id and service.service_id=goal_service.service_id and client_goal_service.goal_service_id=goal_service.goal_service_id and client_goal_service.client_employment_id=?"; 
		return jdbcTemplateObject.query(SQL, new GoalServiceMapper(), employmentId); 
	}

}
