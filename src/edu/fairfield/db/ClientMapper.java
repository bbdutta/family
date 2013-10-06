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
		java.sql.Date dob = rs.getDate("dob");
		client.setDob(dob.toString());
		client.setEducationLevel(rs.getString("educational_level"));
		client.setFirstName(rs.getString("first_name"));
		client.setGender(rs.getString("gender"));
		client.setInmateNum(rs.getLong("inmate_number"));
		client.setLastName(rs.getString("last_name"));
		client.setMiddleName(rs.getString("middle_name"));
		client.setRace(rs.getString("race"));
		client.setProgramId(rs.getLong("program_id"));
		java.sql.Date doa = rs.getDate("date_admitted");
		if (doa != null)
		client.setAdmittedOn(doa.toString());
		client.setTanfEligible(rs.getString("tanf_eligible"));
		java.sql.Date dod = rs.getDate("date_discharged");
		if (dod != null)
			client.setDod(dod.toString());
		client.setReferralSource(rs.getString("referral_source"));
		client.setAppointments(rs.getString("attended_all_appts"));
		client.setHousing(rs.getString("client_housing"));
		client.setNumdrugtests(rs.getInt("num_drug_test"));
		client.setNumpositive(rs.getInt("num_pos_durg_test"));
		client.setTestoncepermonth(rs.getString("drug_test_monthly"));
		client.setDischargeplan(rs.getString("discharge_plan"));
		client.setDischargeReason(rs.getString("discharge_reason"));
		client.setDismissReason(rs.getString("dismissal_reason"));
		client.setCommlinkages(rs.getString("community_linkages"));
		client.setTrtModality(rs.getString("treatment_modality"));
		client.setMedicationAssisted(rs.getString("medication_assisted"));
		client.setValidatedTrt(rs.getString("validated_treatment"));
		
		return client;
	}

}
