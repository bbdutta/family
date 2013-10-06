package edu.fairfield;

import java.util.ArrayList;
import java.util.List;

public class Employment {

	private long employmentId;
	private long clientId;
	private long programId;
	private long inmateNum;
	private String neededBenefits="";
	private String appliedBenefits="";
	private String receivedBenefits="";
	private String neededId="";
	private String appliedId="";
	private String receivedId="";
	private String isEmployed="";
	private String dateOfHire="";
	private String isFulltime="";
	private String isParttime="";
	private Double hourlyRate;
	private String employerName="";
	private String jobRetention30="";
	private String jobRetention60="";
	private String jobRetention90="";
	private String jobRetention90Plus="";
	private String socialReunification="";
	private String enrolledJobReadiness="";
	private String completedJobRediness="";
	private List<GoalService> selectedGoalService= new ArrayList<GoalService>();

	
	public long getEmploymentId() {
		return employmentId;
	}

	public void setEmploymentId(long employmentId) {
		this.employmentId = employmentId;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public long getProgramId() {
		return programId;
	}

	public void setProgramId(long programId) {
		this.programId = programId;
	}

	public String getNeededBenefits() {
		return neededBenefits;
	}

	public void setNeededBenefits(String neededBenefits) {
		this.neededBenefits = neededBenefits;
	}

	public String getAppliedBenefits() {
		return appliedBenefits;
	}

	public void setAppliedBenefits(String appliedBenefits) {
		this.appliedBenefits = appliedBenefits;
	}

	public String getReceivedBenefits() {
		return receivedBenefits;
	}

	public void setReceivedBenefits(String receivedBenefits) {
		this.receivedBenefits = receivedBenefits;
	}

	public String getNeededId() {
		return neededId;
	}

	public void setNeededId(String neededId) {
		this.neededId = neededId;
	}

	public String getAppliedId() {
		return appliedId;
	}

	public void setAppliedId(String appliedId) {
		this.appliedId = appliedId;
	}

	public String getReceivedId() {
		return receivedId;
	}

	public void setReceivedId(String receivedId) {
		this.receivedId = receivedId;
	}

	public String getIsEmployed() {
		return isEmployed;
	}

	public void setIsEmployed(String isEmployed) {
		this.isEmployed = isEmployed;
	}

	public String getDateOfHire() {
		return dateOfHire;
	}

	public void setDateOfHire(String dateOfHire) {
		this.dateOfHire = dateOfHire;
	}

	public String getIsFulltime() {
		return isFulltime;
	}

	public void setIsFulltime(String isFulltime) {
		this.isFulltime = isFulltime;
	}

	public String getIsParttime() {
		return isParttime;
	}

	public void setIsParttime(String isParttime) {
		this.isParttime = isParttime;
	}

	public Double getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(Double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public String getEmployerName() {
		return employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public String getJobRetention30() {
		return jobRetention30;
	}

	public void setJobRetention30(String jobRetention30) {
		this.jobRetention30 = jobRetention30;
	}

	public String getJobRetention60() {
		return jobRetention60;
	}

	public void setJobRetention60(String jobRetention60) {
		this.jobRetention60 = jobRetention60;
	}

	public String getJobRetention90() {
		return jobRetention90;
	}

	public void setJobRetention90(String jobRetention90) {
		this.jobRetention90 = jobRetention90;
	}

	public String getJobRetention90Plus() {
		return jobRetention90Plus;
	}

	public void setJobRetention90Plus(String jobRetention90Plus) {
		this.jobRetention90Plus = jobRetention90Plus;
	}

	public String getSocialReunification() {
		return socialReunification;
	}

	public void setSocialReunification(String socialReunification) {
		this.socialReunification = socialReunification;
	}

	public String getEnrolledJobReadiness() {
		return enrolledJobReadiness;
	}

	public void setEnrolledJobReadiness(String enrolledJobReadiness) {
		this.enrolledJobReadiness = enrolledJobReadiness;
	}

	public String getCompletedJobRediness() {
		return completedJobRediness;
	}

	public void setCompletedJobRediness(String completedJobRediness) {
		this.completedJobRediness = completedJobRediness;
	}

	public List<GoalService> getSelectedGoalService() {
		return selectedGoalService;
	}

	public void setSelectedGoalService(List<GoalService> selectedGoalService) {
		this.selectedGoalService = selectedGoalService;
	}

	public long getInmateNum() {
		return inmateNum;
	}

	public void setInmateNum(long inmateNum) {
		this.inmateNum = inmateNum;
	}

}
