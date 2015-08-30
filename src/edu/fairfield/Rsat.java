package edu.fairfield;

import java.util.ArrayList;
import java.util.List;

public class Rsat {

	private Client client;
	private String progAddDate;
	private String orientationDate;
	private String orientationFacility;
	private String receivedRiskAsmt;
	private String assmtDate;
	private String toolNameUsed;
	private String highCrimeogenicRisk;
	private String completedIndTrtPlan;
	private String enrolledRsatAftercare;
	private String aftercareEnrollDate;
	private String contCareAgmt;
	private String dateOfService;
	private String typeOfService;
	private String otherService;
	private String compAllAftercareReq;
	private String dateOfCompletion;
	private String reasonNonCompletion;
	private String otherReason;
	private String dateOfDrugTest;
	private String testedPositiveSubstance;
	private int noOfUrineTest;
	private String agenciesAssistedClient;
	private String healthCare;
	private String enrolledInMedicaid;
	private String progCompDate;
	private String haveInsurance;
	private String insuranceType;
		
	public static List<String> serviceList = new ArrayList();
	public static List<String> nonCompletionList = new ArrayList();
	public static List<String> healthCareList = new ArrayList();
	public static List<String> insuranceTypeList = new ArrayList();
	
	static {

		serviceList.add("");
		serviceList.add("Substance Abuse and treatment services");
		serviceList.add("Cognitive and behavioral services");
		serviceList.add("Employment services");
		serviceList.add("Housing services");
		serviceList.add("Mental Health services");
		serviceList.add("Other services");
		
		nonCompletionList.add("");
		nonCompletionList.add("Failure to meet program");
		nonCompletionList.add("Court of Criminal involvement");
		nonCompletionList.add("Voluntary drop out");
		nonCompletionList.add("Absconding");
		nonCompletionList.add("Relocating or case transfer");
		nonCompletionList.add("Death or serious illness");
		nonCompletionList.add("Other reason");
		
		healthCareList.add("Private health insurance");
		healthCareList.add("Employment based health insurance");
		healthCareList.add("Self employment based health insurance");
		healthCareList.add("Direct-purchase health insurance");
		healthCareList.add("Medicaid");
	
		insuranceTypeList.add("");
		insuranceTypeList.add("Humana");
		insuranceTypeList.add("Husky A");
		insuranceTypeList.add("Husky B");
		insuranceTypeList.add("Husky C");
		insuranceTypeList.add("Husky D");
		insuranceTypeList.add("Anthem Blue Cross Blue Shield");
		insuranceTypeList.add("Connecticare");
		insuranceTypeList.add("Aetna Medicate: Social Security Administration");
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getReceivedRiskAsmt() {
		return receivedRiskAsmt;
	}

	public void setReceivedRiskAsmt(String receivedRiskAsmt) {
		this.receivedRiskAsmt = receivedRiskAsmt;
	}

	public String getAssmtDate() {
		return assmtDate;
	}

	public void setAssmtDate(String assmtDate) {
		this.assmtDate = assmtDate;
	}

	public String getToolNameUsed() {
		return toolNameUsed;
	}

	public void setToolNameUsed(String toolNameUsed) {
		this.toolNameUsed = toolNameUsed;
	}

	public String getHighCrimeogenicRisk() {
		return highCrimeogenicRisk;
	}

	public void setHighCrimeogenicRisk(String highCrimeogenicRisk) {
		this.highCrimeogenicRisk = highCrimeogenicRisk;
	}

	public String getCompletedIndTrtPlan() {
		return completedIndTrtPlan;
	}

	public void setCompletedIndTrtPlan(String completedIndTrtPlan) {
		this.completedIndTrtPlan = completedIndTrtPlan;
	}

	public String getEnrolledRsatAftercare() {
		return enrolledRsatAftercare;
	}

	public void setEnrolledRsatAftercare(String enrolledRsatAftercare) {
		this.enrolledRsatAftercare = enrolledRsatAftercare;
	}

	public String getAftercareEnrollDate() {
		return aftercareEnrollDate;
	}

	public void setAftercareEnrollDate(String aftercareEnrollDate) {
		this.aftercareEnrollDate = aftercareEnrollDate;
	}

	public String getContCareAgmt() {
		return contCareAgmt;
	}

	public void setContCareAgmt(String contCareAgmt) {
		this.contCareAgmt = contCareAgmt;
	}

	public String getDateOfService() {
		return dateOfService;
	}

	public void setDateOfService(String dateOfService) {
		this.dateOfService = dateOfService;
	}

	public String getTypeOfService() {
		return typeOfService;
	}

	public void setTypeOfService(String typeOfService) {
		this.typeOfService = typeOfService;
	}

	public String getOtherService() {
		return otherService;
	}

	public void setOtherService(String otherService) {
		this.otherService = otherService;
	}

	public String getCompAllAftercareReq() {
		return compAllAftercareReq;
	}

	public void setCompAllAftercareReq(String compAllAftercareReq) {
		this.compAllAftercareReq = compAllAftercareReq;
	}

	public String getDateOfCompletion() {
		return dateOfCompletion;
	}

	public void setDateOfCompletion(String dateOfCompletion) {
		this.dateOfCompletion = dateOfCompletion;
	}

	public String getReasonNonCompletion() {
		return reasonNonCompletion;
	}

	public void setReasonNonCompletion(String reasonNonCompletion) {
		this.reasonNonCompletion = reasonNonCompletion;
	}

	public String getOtherReason() {
		return otherReason;
	}

	public void setOtherReason(String otherReason) {
		this.otherReason = otherReason;
	}

	public String getDateOfDrugTest() {
		return dateOfDrugTest;
	}

	public void setDateOfDrugTest(String dateOfDrugTest) {
		this.dateOfDrugTest = dateOfDrugTest;
	}

	public String getTestedPositiveSubstance() {
		return testedPositiveSubstance;
	}

	public void setTestedPositiveSubstance(String testedPositiveSubstance) {
		this.testedPositiveSubstance = testedPositiveSubstance;
	}

	public String getHealthCare() {
		return healthCare;
	}

	public void setHealthCare(String healthCare) {
		this.healthCare = healthCare;
	}

	public String getEnrolledInMedicaid() {
		return enrolledInMedicaid;
	}

	public void setEnrolledInMedicaid(String enrolledInMedicaid) {
		this.enrolledInMedicaid = enrolledInMedicaid;
	}

	public String getProgAddDate() {
		return progAddDate;
	}

	public void setProgAddDate(String progAddDate) {
		this.progAddDate = progAddDate;
	}

	public String getOrientationDate() {
		return orientationDate;
	}

	public void setOrientationDate(String orientationDate) {
		this.orientationDate = orientationDate;
	}

	public String getOrientationFacility() {
		return orientationFacility;
	}

	public void setOrientationFacility(String orientationFacility) {
		this.orientationFacility = orientationFacility;
	}

	public int getNoOfUrineTest() {
		return noOfUrineTest;
	}

	public void setNoOfUrineTest(int noOfUrineTest) {
		this.noOfUrineTest = noOfUrineTest;
	}

	public String getAgenciesAssistedClient() {
		return agenciesAssistedClient;
	}

	public void setAgenciesAssistedClient(String agenciesAssistedClient) {
		this.agenciesAssistedClient = agenciesAssistedClient;
	}

	public String getProgCompDate() {
		return progCompDate;
	}

	public void setProgCompDate(String progCompDate) {
		this.progCompDate = progCompDate;
	}

	public String getHaveInsurance() {
		return haveInsurance;
	}

	public void setHaveInsurance(String haveInsurance) {
		this.haveInsurance = haveInsurance;
	}

	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	
}
