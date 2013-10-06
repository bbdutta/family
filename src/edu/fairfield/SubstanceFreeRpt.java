package edu.fairfield;

public class SubstanceFreeRpt {

	private long testedPositiveNum;
	private long testedOncePerMonthNum;
	private long totalDrugTestNum;
	
	private float testedPositivePct;
	private float testedOncePerMonthPct;
	
	
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
	
	public long getTestedPositiveNum() {
		return testedPositiveNum;
	}
	public void setTestedPositiveNum(long testedPositiveNum) {
		this.testedPositiveNum = testedPositiveNum;
	}
	public long getTestedOncePerMonthNum() {
		return testedOncePerMonthNum;
	}
	public void setTestedOncePerMonthNum(long testedOncePerMonthNum) {
		this.testedOncePerMonthNum = testedOncePerMonthNum;
	}
	public long getTotalDrugTestNum() {
		return totalDrugTestNum;
	}
	public void setTotalDrugTestNum(long totalDrugTestNum) {
		this.totalDrugTestNum = totalDrugTestNum;
	}
	public float getTestedPositivePct() {
		if (totalDrugTestNum > 0)
		this.testedPositivePct = testedPositiveNum*100/totalDrugTestNum;
		return testedPositivePct;
	}
	public void setTestedPositivePct() {
		if (totalDrugTestNum > 0)
		this.testedPositivePct = testedPositiveNum*100/totalDrugTestNum;
	}
	public float getTestedOncePerMonthPct() {
		if (totalDrugTestNum > 0)
		this.testedOncePerMonthPct = testedOncePerMonthNum*100/totalDrugTestNum;
		return testedOncePerMonthPct;
	}
	public void setTestedOncePerMonthPct() {
		if (totalDrugTestNum > 0)
		this.testedOncePerMonthPct = testedOncePerMonthNum*100/totalDrugTestNum;
	}

	
}
