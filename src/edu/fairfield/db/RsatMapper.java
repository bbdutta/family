package edu.fairfield.db;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.fairfield.Rsat;

public class RsatMapper implements RowMapper<Rsat>{

	@Override
	public Rsat mapRow(ResultSet rs, int rowNum) throws SQLException {

 		Rsat rsat = new Rsat();
 		Date progAddDate = rs.getDate("admission_date");
		if (progAddDate != null) {
			rsat.setProgAddDate(progAddDate.toString());
		}
 		Date orientationDate = rs.getDate("orientation_date");
		if (orientationDate != null) {
			rsat.setOrientationDate(orientationDate.toString());
		}
		rsat.setOrientationFacility(rs.getString("orientation_facility"));
		rsat.setReceivedRiskAsmt(rs.getString("received_risk_asmt"));
		Date assmtDate = rs.getDate("assmt_date");
		if (assmtDate != null) {
			rsat.setAssmtDate(assmtDate.toString());
		}
		rsat.setToolNameUsed(rs.getString("toolname_used"));
		rsat.setHighCrimeogenicRisk(rs.getString("high_crimeogenic_risk"));
		rsat.setCompletedIndTrtPlan(rs.getString("completed_ind_trt_plan"));
		rsat.setEnrolledRsatAftercare(rs.getString("enrolled_rsat_aftercare"));
		Date aftercareEnrollDate = rs.getDate("aftercare_enroll_date");
		if (aftercareEnrollDate != null) {
			rsat.setAftercareEnrollDate(aftercareEnrollDate.toString());
		}
		rsat.setContCareAgmt(rs.getString("cont_care_agmt"));
		Date dateOfService = rs.getDate("service_date");
		if (dateOfService != null) {
			rsat.setDateOfService(dateOfService.toString());
		}
		rsat.setTypeOfService(rs.getString("service_type"));
		rsat.setOtherService(rs.getString("other_service"));
		rsat.setCompAllAftercareReq(rs.getString("comp_all_aftercare_req"));
		Date dateOfCompletion = rs.getDate("completion_date");
		if (dateOfCompletion != null) {
			rsat.setDateOfCompletion(dateOfCompletion.toString());
		}
		rsat.setReasonNonCompletion(rs.getString("reason_non_completion"));
		rsat.setOtherReason(rs.getString("other_reason"));
		Date dateOfDrugTest = rs.getDate("drug_test_date");
		if (dateOfDrugTest != null) {
			rsat.setDateOfDrugTest(dateOfDrugTest.toString());
		}
		rsat.setTestedPositiveSubstance(rs.getString("tested_positive_sub"));
		rsat.setNoOfUrineTest(rs.getInt("no_of_urine_test"));
		rsat.setAgenciesAssistedClient(rs.getString("agencies_assisted_client"));
		rsat.setHealthCare(rs.getString("health_care_provider"));
		rsat.setEnrolledInMedicaid(rs.getString("enrolled_medicaid"));
		Date progCompDate = rs.getDate("program_completion_date");
		if (progCompDate != null) {
			rsat.setProgCompDate(progCompDate.toString());
		}
		
		return rsat;
	}

}
