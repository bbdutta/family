package edu.fairfield;

import java.util.ArrayList;
import java.util.List;

public class Client {

	private long clientId;
	private long inmateNum;
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String race;
	private String dob;
	private String admittedOn;
	private String educationLevel;
	private String tanfEligible;
	private long programId;
	private String dod;
	private int lengthofStay=0;
	private String appointments;
	private int numdrugtests;
	private String testoncepermonth;
	private int numpositive;
	private String dischargeplan;
	private String commlinkages;
	private String housing;
	private String dischargeReason;
	private String dismissReason;
	private String referralSource;
	private String trtModality;
	private String medicationAssisted;
	private String validatedTrt;
	
	public static List<String> raceList = new ArrayList();
	public static List<String> eduList = new ArrayList();
	public static List<String> refSrcList = new ArrayList();
	public static List<String> housingList = new ArrayList();
	public static List<String> numList = new ArrayList();
	public static List<String> dischargeList = new ArrayList();
	public static List<String> dismissList = new ArrayList();
	public static List<String> trtModalityList = new ArrayList();
	public static List<String> medicationAssistedList = new ArrayList();
	
	static {
		//raceList.add("");
		raceList.add("White/Caucasian");
		raceList.add("Hispanic");
		raceList.add("Black/African");
		raceList.add("American");
		raceList.add("Asian/Pacific Islander");
		raceList.add("Other");
		
		//eduList.add("");
		eduList.add("HS diploma");
		eduList.add("GED");
		eduList.add("Up to Grade 7");
		eduList.add("Up to Grade 8");
		eduList.add("Up to Grade 9");
		eduList.add("Up to Grade 10");
		eduList.add("Up to Grade 11");
		
		//refSrcList.add("");
		refSrcList.add("Parole Officer");
		refSrcList.add("TS Officer");
		refSrcList.add("Halfway House");
		refSrcList.add("Counselor");
		refSrcList.add("Home");
		refSrcList.add("RSAT");
		
		housingList.add("Home");
		housingList.add("Half-way house");
		
		numList.add("0");
		numList.add("1");
		numList.add("2");
		numList.add("3");
		numList.add("4");
		numList.add("5");
		numList.add("6");
		numList.add("7");
		numList.add("8");
		numList.add("9");
		numList.add("10");
		
		dischargeList.add("");
		dischargeList.add("Program completion");
		dischargeList.add("Remand");
		dischargeList.add("Dismissal");
		
		dismissList.add("");
		dismissList.add("Loss of contact");
		dismissList.add("Program rule violation");
		dismissList.add("EOS");
		dismissList.add("Illness/Hospitalization");
		dismissList.add("Death");
		dismissList.add("Transfer");

		trtModalityList.add("");
		trtModalityList.add("Individual counseling");
		trtModalityList.add("Family counseling");
		trtModalityList.add("Couples counseling");
		trtModalityList.add("Group counseling");
		trtModalityList.add("None");
		
		medicationAssistedList.add("");
		medicationAssistedList.add("Methadone");
		medicationAssistedList.add("Anti-buse");
		medicationAssistedList.add("Vitriol");
		medicationAssistedList.add("Psychotropic medication");
		medicationAssistedList.add("None");

	}
	
	public long getClientId() {
		return clientId;
	}
	public void setClientId(long clientId) {
		this.clientId = clientId;
	}
	public long getInmateNum() {
		return inmateNum;
	}
	public void setInmateNum(long inmateNum) {
		this.inmateNum = inmateNum;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getEducationLevel() {
		return educationLevel;
	}
	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}
	public String getTanfEligible() {
		return tanfEligible;
	}
	public void setTanfEligible(String tanfEligible) {
		this.tanfEligible = tanfEligible;
	}
	public long getProgramId() {
		return programId;
	}
	public void setProgramId(long programId) {
		this.programId = programId;
	}
	public String getAdmittedOn() {
		return admittedOn;
	}
	public void setAdmittedOn(String admittedOn) {
		this.admittedOn = admittedOn;
	}
	public String getDod() {
		return dod;
	}
	public void setDod(String dod) {
		this.dod = dod;
	}
	public int getLengthofStay() {
		return lengthofStay;
	}
	public void setLengthofStay(int lengthofStay) {
		this.lengthofStay = lengthofStay;
	}
	public String getAppointments() {
		return appointments;
	}
	public void setAppointments(String appointments) {
		this.appointments = appointments;
	}
	public int getNumdrugtests() {
		return numdrugtests;
	}
	public void setNumdrugtests(int numdrugtests) {
		this.numdrugtests = numdrugtests;
	}
	public String getTestoncepermonth() {
		return testoncepermonth;
	}
	public void setTestoncepermonth(String testoncepermonth) {
		this.testoncepermonth = testoncepermonth;
	}
	public int getNumpositive() {
		return numpositive;
	}
	public void setNumpositive(int numpositive) {
		this.numpositive = numpositive;
	}
	public String getDischargeplan() {
		return dischargeplan;
	}
	public void setDischargeplan(String dischargeplan) {
		this.dischargeplan = dischargeplan;
	}
	public String getCommlinkages() {
		return commlinkages;
	}
	public void setCommlinkages(String commlinkages) {
		this.commlinkages = commlinkages;
	}
	public String getHousing() {
		return housing;
	}
	public void setHousing(String housing) {
		this.housing = housing;
	}
	public String getDischargeReason() {
		return dischargeReason;
	}
	public void setDischargeReason(String dischargeReason) {
		this.dischargeReason = dischargeReason;
	}
	public String getDismissReason() {
		return dismissReason;
	}
	public void setDismissReason(String dismissReason) {
		this.dismissReason = dismissReason;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getReferralSource() {
		return referralSource;
	}
	public void setReferralSource(String referralSource) {
		this.referralSource = referralSource;
	}
	public String getTrtModality() {
		return trtModality;
	}
	public void setTrtModality(String trtModality) {
		this.trtModality = trtModality;
	}
	public String getMedicationAssisted() {
		return medicationAssisted;
	}
	public void setMedicationAssisted(String medicationAssisted) {
		this.medicationAssisted = medicationAssisted;
	}
	public String getValidatedTrt() {
		return validatedTrt;
	}
	public void setValidatedTrt(String validatedTrt) {
		this.validatedTrt = validatedTrt;
	}
	
	
	
}
