package edu.fairfield.db;

import java.util.Calendar;
import java.util.List; 
import javax.sql.DataSource;

import edu.fairfield.Client;

public interface ClientDAO {

	/** 
	 * This is the method to be used to initialize 
	 * database resources ie. connection.
	 */ 
	public void setDataSource(DataSource ds); 
	
	/** 
	 * This is the method to be used to create 
	 * a record in the Client table. 
	 */ 
	public void create(long inmateNum, String firstName, String middleName, String lastName, String gender, Calendar dob, String educationLevel); 
	
	/** 
	 * This is the method to be used to list down 
	 * a record from the Client table corresponding 
	 * to a passed client id. 
	 * 
	 */ 
	public Client getClient(Long id); 
	
	/** 
	 * This is the method to be used to list down 
	 * all the records from the Client table. 
	 */
	public List<Client> listClients(); 
	
	/** 
	 * This is the method to be used to delete 
	 * a record from the Client table corresponding 
	 * to a passed client id. 
	 */ 
	public void delete(Long id); 
	
	/** 
	 * This is the method to be used to update 
	 * a record into the Client table. 
	 */ 
	public void update(Long id, Integer age);
}
