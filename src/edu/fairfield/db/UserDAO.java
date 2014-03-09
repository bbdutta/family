package edu.fairfield.db;

import java.util.List;

import javax.sql.DataSource;

import edu.fairfield.User;

public interface UserDAO {

	/** 
	 * This is the method to be used to initialize 
	 * database resources ie. connection.
	 */ 
	public void setDataSource(DataSource ds); 
	
	/** 
	 * This is the method to be used to create 
	 * a record in the user table. 
	 */ 
	public void create(String userName, String password, String firstName, String middleName, String lastName, String email, String phoneNo); 
	
	/** 
	 * This is the method to be used to validate
	 * userName and password. 
	 * 
	 */ 
	public User login(String userName, String password); 

	/** 
	 * This is the method to be used to list down 
	 * a record from the user table corresponding 
	 * to a passed user id. 
	 * 
	 */ 
	public User getUser(Long id); 
	
	/** 
	 * This is the method to be used to list down 
	 * all the records from the user table. 
	 */
	public List<User> listUsers(); 
	
	/** 
	 * This is the method to be used to delete 
	 * a record from the user table corresponding 
	 * to a passed user id. 
	 */ 
	public void delete(Long id); 
	
	/** 
	 * This is the method to be used to list down 
	 * a record from the user_program table corresponding 
	 * to a passed user id. 
	 * 
	 */ 
	public long getUserProgramId(Long id); 
	
	/** 
	 * This is the method to be used to list down 
	 * a record from the user_program table corresponding 
	 * to a passed user id. 
	 * 
	 */ 
	public String getUserRole(Long id); 
	
	public int createUserRole(Long userId, Long roleId);
	
	public int createUserProgram(Long userId, Long programId);
}
