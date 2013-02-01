package edu.fairfield.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.fairfield.Client;

public class ClientMapper implements RowMapper<Client>{

	@Override
	public Client mapRow(ResultSet rs, int rowNum) throws SQLException {

		Client client = new Client();
		client.setClientId(rs.getLong("client_id"));
		java.sql.Date sqlDate = rs.getDate("dob");
		client.setDob(sqlDate.toString());
		client.setEducationLevel(rs.getString("educational_level"));
		client.setFirstName(rs.getString("first_name"));
		client.setGender(rs.getString("gender"));
		client.setInmateNum(rs.getLong("inmate_number"));
		client.setLastName(rs.getString("last_name"));
		client.setMiddleName(rs.getString("middle_name"));
		
		return client;
	}

	
}
