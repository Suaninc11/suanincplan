package com.suaninc.newsagency.domain;

import java.util.Date;

public class CarrierTemplate {
	
	private Integer carrierTemplateId;
    private String templateCode;
    private String templateName;
    private Integer templateImageOrder;
    private String templateImageName;
    private String templateImageUrl;
    private String lastUpdatedDatetime;
    private String registeredDatetime;
    
    private String templateCount;
    private Integer rowNum;
    
	public Integer getCarrierTemplateId() {
		return carrierTemplateId;
	}
	public void setCarrierTemplateId(Integer carrierTemplateId) {
		this.carrierTemplateId = carrierTemplateId;
	}
	public String getTemplateCode() {
		return templateCode;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public Integer getTemplateImageOrder() {
		return templateImageOrder;
	}
	public void setTemplateImageOrder(Integer templateImageOrder) {
		this.templateImageOrder = templateImageOrder;
	}
	public String getTemplateImageName() {
		return templateImageName;
	}
	public void setTemplateImageName(String templateImageName) {
		this.templateImageName = templateImageName;
	}
	public String getTemplateImageUrl() {
		return templateImageUrl;
	}
	public void setTemplateImageUrl(String templateImageUrl) {
		this.templateImageUrl = templateImageUrl;
	}
	public String getLastUpdatedDatetime() {
		return lastUpdatedDatetime;
	}
	public void setLastUpdatedDatetime(String lastUpdatedDatetime) {
		this.lastUpdatedDatetime = lastUpdatedDatetime;
	}
	public String getRegisteredDatetime() {
		return registeredDatetime;
	}
	public void setRegisteredDatetime(String registeredDatetime) {
		this.registeredDatetime = registeredDatetime;
	}
	public String getTemplateCount() {
		return templateCount;
	}
	public void setTemplateCount(String templateCount) {
		this.templateCount = templateCount;
	}
	public Integer getRowNum() {
		return rowNum;
	}
	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}
	
}