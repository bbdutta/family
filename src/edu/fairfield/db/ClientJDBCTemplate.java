package edu.fairfield.db;

import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import edu.fairfield.Client;

public class ClientJDBCTemplate implements ClientDAO {

	private DataSource dataSource; 
	private JdbcTemplate jdbcTemplateObject;
	
	@Override
	public void setDataSource(DataSource ds) {
		
		this.dataSource = ds;
		this.jdbcTemplateObject = new JdbcTemplate(ds);
	}

	@Override
	public void create(long inmateNum, String firstName, String middleName,
			String lastName, String gender, Calendar dob, String educationLevel) {
					
		String SQL = "insert into Client (inmate_number, first_name, middle_name, last_name, gender, dob, educational_level) " +
				"values (?, ?, ?, ?, ?, ?, ?)"; 
		jdbcTemplateObject.update( SQL, inmateNum, firstName, middleName, lastName, gender, dob, educationLevel); 
		System.out.println("Created Record Name=" + firstName + " " + lastName); 
	}

	@Override
	public Client getClient(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> listClients() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Long id, Integer age) {
		// TODO Auto-generated method stub
		
	}


}
