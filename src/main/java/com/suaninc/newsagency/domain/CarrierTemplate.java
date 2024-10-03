package com.suaninc.newsagency.domain;

import java.util.Date;

public class CarrierTemplate {
	
	private Integer carrierTemplateId;
    private String templateCode;
    private String templateName;
    private Integer templateImageOrder;
    private String templateImageName;
    private Date registeredDatetime;
    
    private String templateCount;
    
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
	public Date getRegisteredDatetime() {
		return registeredDatetime;
	}
	public void setRegisteredDatetime(Date registeredDatetime) {
		this.registeredDatetime = registeredDatetime;
	}
	public String getTemplateCount() {
		return templateCount;
	}
	public void setTemplateCount(String templateCount) {
		this.templateCount = templateCount;
	}
    
}