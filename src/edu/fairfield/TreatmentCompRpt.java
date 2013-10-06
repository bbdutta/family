package edu.fairfield;

public class TreatmentCompRpt {

	private long attendedAllAppNum;;
	private long missedAppNum;
	private long stillInAssessmentNum;

	
	private String startDate;
	private String endDate;
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public long getAttendedAllAppNum() {
		return attendedAllAppNum;
	}
	public void setAttendedAllAppNum(long attendedAllAppNum) {
		this.attendedAllAppNum = attendedAllAppNum;
	}
	public long getMissedAppNum() {
		return missedAppNum;
	}
	public void setMissedAppNum(long missedAppNum) {
		this.missedAppNum = missedAppNum;
	}
	public long getStillInAssessmentNum() {
		return stillInAssessmentNum;
	}
	public void setStillInAssessmentNum(long stillInAssessmentNum) {
		this.stillInAssessmentNum = stillInAssessmentNum;
	}
	
	
}
