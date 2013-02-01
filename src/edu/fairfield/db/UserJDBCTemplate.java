package edu.fairfield.db;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import edu.fairfield.User;

public class UserJDBCTemplate implements UserDAO {

	private DataSource dataSource; 
	private JdbcTemplate jdbcTemplateObject;
	
	@Override
	public void setDataSource(DataSource ds) {
		
		this.dataSource = ds;
		this.jdbcTemplateObject = new JdbcTemplate(ds);
	}

	@Override
	public void create(String userName, String password, String firstName,
			String middleName, String lastName, String email, String phoneNum) {
		
		String SQL = "insert into user (username, password, first_name, middle_name, last_name, email, phone_number) " +
				"values (?, ?, ?, ?, ?, ?, ?)"; 
		jdbcTemplateObject.update( SQL, userName, password, firstName, middleName, lastName, email, phoneNum); 
		System.out.println("Created Record Name=" + firstName + " " + lastName); 

	}

	@Override
	public User getUser(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> listUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

}
