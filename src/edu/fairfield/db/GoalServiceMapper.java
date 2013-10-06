package edu.fairfield.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.fairfield.GoalService;

public class GoalServiceMapper implements RowMapper<GoalService> {

	@Override
	public GoalService mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		GoalService goalService = new GoalService();
		goalService.setGoalServiceId(rs.getInt("goal_service_id"));
		goalService.setGoalId(rs.getInt("goal_id"));
		goalService.setGoalName(rs.getString("goal_name"));
		goalService.setServicelId(rs.getInt("service_id"));
		goalService.setServiceName(rs.getString("service_name"));
		
		return goalService;
	}

}
