package com.test.Swagger;

public class EodReportDto {
	
	private String channelId;
	private String branchId;
	private String fimp;
	private String agentIdentifier;
	private String reportDate;
	private String pdfBytes;
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getFimp() {
		return fimp;
	}
	public void setFimp(String fimp) {
		this.fimp = fimp;
	}
	public String getAgentIdentifier() {
		return agentIdentifier;
	}
	public void setAgentIdentifier(String agentIdentifier) {
		this.agentIdentifier = agentIdentifier;
	}
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public String getPdfBytes() {
		return pdfBytes;
	}
	public void setPdfBytes(String pdfBytes) {
		this.pdfBytes = pdfBytes;
	}
	public EodReportDto() {
		
	}
	
	

}
