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
		String SQL = "SELECT u.* FROM user u"; 
		return jdbcTemplateObject.query(SQL, new UserMapper());
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
	
	@Override
	public int createUserRole(Long userId, Long roleId) {
		String selSQL = "select count(*) cnt from user_role where user_id = ?";
		long cnt = jdbcTemplateObject.queryForObject(selSQL, new Object[] { userId }, Long.class);
		int result = 0;
		if (cnt < 1) {
			String insSQL = "insert into user_role (user_id, role_id) " +
					"values (?, ?)"; 
			result = jdbcTemplateObject.update( insSQL, userId, roleId); 
			System.out.println("Created Role for UserID" + userId + " RoleID" + roleId); 
		} else {
			result = -1;
		}
		return result;
	}	

	@Override
	public int createUserProgram(Long userId, Long programId) {
		String selSQL = "select count(*) cnt from user_program where user_id = ?";
		long cnt = jdbcTemplateObject.queryForObject(selSQL, new Object[] { userId }, Long.class);
		int result = 0;
		if (cnt < 1) {
			String insSQL = "insert into user_program (user_id, program_id) " +
					"values (?, ?)"; 
			result = jdbcTemplateObject.update( insSQL, userId, programId); 
			System.out.println("Created Program for UserID" + userId + " ProgramID" + programId); 
		} else {
			result = -1;
		}
		return result;
	}	

}
