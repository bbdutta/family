package edu.fairfield;

public class DischargePlanRpt {

	private long idpNum;;
	private long preLinkComNum;
	private long less30NoPlanNum;
	
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
	public long getIdpNum() {
		return idpNum;
	}
	public void setIdpNum(long idpNum) {
		this.idpNum = idpNum;
	}
	public long getPreLinkComNum() {
		return preLinkComNum;
	}
	public void setPreLinkComNum(long preLinkComNum) {
		this.preLinkComNum = preLinkComNum;
	}
	public long getLess30NoPlanNum() {
		return less30NoPlanNum;
	}
	public void setLess30NoPlanNum(long less30NoPlanNum) {
		this.less30NoPlanNum = less30NoPlanNum;
	}
	
	
}
