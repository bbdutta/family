package edu.fairfield.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.fairfield.Goal;

public class GoalMapper implements RowMapper<Goal> {

	@Override
	public Goal mapRow(ResultSet rs, int rowNum) throws SQLException {
		Goal goal = new Goal();
		goal.setGoalId(rs.getInt("goal_id"));
		goal.setGoalName(rs.getString("goal_name"));
		return goal;
	}

}
