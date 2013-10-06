package edu.fairfield.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.fairfield.Client;
import edu.fairfield.Employment;

public class EmploymentMapper implements RowMapper<Employment>{

	@Override
	public Employment mapRow(ResultSet rs, int rowNum) throws SQLException {

		Employment e = new Employment();
		e.setEmploymentId(rs.getLong("client_employment_id"));
		e.setClientId(rs.getLong("client_id"));
		e.setProgramId(rs.getLong("program_id"));
		e.setInmateNum(rs.getLong("inmate_number"));
		java.sql.Date doh = rs.getDate("date_of_hire");
		if (doh != null)
			e.setDateOfHire(doh.toString());
		e.setNeededBenefits(rs.getString("needed_benefits"));
		e.setAppliedBenefits(rs.getString("applied_benefits"));
		e.setReceivedBenefits(rs.getString("received_benefits"));
		e.setNeededId(rs.getString("needed_id"));
		e.setAppliedId(rs.getString("applied_id"));
		e.setReceivedId(rs.getString("received_id"));
		e.setIsEmployed(rs.getString("is_employed"));
		e.setIsFulltime(rs.getString("is_fulltime"));
		e.setIsParttime(rs.getString("is_parttime"));
		e.setHourlyRate(rs.getDouble("hourly_rate"));
		e.setEmployerName(rs.getString("employer_name"));
		e.setJobRetention30(rs.getString("job_retention_30"));
		e.setJobRetention60(rs.getString("job_retention_60"));
		e.setJobRetention90(rs.getString("job_retention_90"));
		e.setJobRetention90Plus(rs.getString("job_retention_90_plus"));
		e.setSocialReunification(rs.getString("social_reunification"));
		e.setEnrolledJobReadiness(rs.getString("enrolled_job_readiness"));
		e.setCompletedJobRediness(rs.getString("completed_job_rediness"));
		
		return e;
	}

}
