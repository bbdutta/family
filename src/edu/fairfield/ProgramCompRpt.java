package edu.fairfield;

public class ProgramCompRpt {

	private long successDischargeNum;;
	private long unsuccessDischargeNum;
	private long otherDischargeNum;
	
	private float successDischargePct;

	
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

	public long getSuccessDischargeNum() {
		return successDischargeNum;
	}

	public void setSuccessDischargeNum(long successDischargeNum) {
		this.successDischargeNum = successDischargeNum;
	}

	public long getUnsuccessDischargeNum() {
		return unsuccessDischargeNum;
	}

	public void setUnsuccessDischargeNum(long unsuccessDischargeNum) {
		this.unsuccessDischargeNum = unsuccessDischargeNum;
	}

	public long getOtherDischargeNum() {
		return otherDischargeNum;
	}

	public void setOtherDischargeNum(long otherDischargeNum) {
		this.otherDischargeNum = otherDischargeNum;
	}

	public float getSuccessDischargePct() {
		long total = successDischargeNum+unsuccessDischargeNum+otherDischargeNum;
		if (total > 0)
			this.successDischargePct = (successDischargeNum*100)/total;
		else
			this.successDischargePct = 0;
		return successDischargePct;
	}

	public void setSuccessDischargePct() {
		long total = successDischargeNum+unsuccessDischargeNum+otherDischargeNum;
		if (total > 0)
			this.successDischargePct = (successDischargeNum*100)/total;
		else
			this.successDischargePct = 0;
	}
	
	
}
