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

	@Override
	public User login(String userName, String password) {

		String SQL = "select * from user where username = ? and password = ?"; 
		return jdbcTemplateObject.queryForObject(SQL,new Object[] { userName, password }, new UserMapper());
		
	}

	@Override
	public long getUserProgramId(Long id) {
		String SQL = "select program_id from user_program where user_id = ?"; 
		return jdbcTemplateObject.queryForLong(SQL,new Object[] { id });
	}

	@Override
	public String getUserRole(Long id) {
		String SQL = "select role_name from user u, user_role ur, role r where u.user_id=ur.user_id and " +
				"ur.role_id=r.role_id and u.user_id = ?"; 
		return jdbcTemplateObject.queryForObject(SQL, new Object[] { id }, String.class);
	}
	
	

}
