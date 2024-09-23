package com.suaninc.newsagency.domain;

import java.util.Date;

public class CarrierTemplate {
	
	private Integer carrierTemplateId;
    private String templateCode;
    private Integer templateImageOrder;
    private String templateImagePath;
    private String templateImageName;
    private Date registeredDatetime;
    
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
	public Integer getTemplateImageOrder() {
		return templateImageOrder;
	}
	public void setTemplateImageOrder(Integer templateImageOrder) {
		this.templateImageOrder = templateImageOrder;
	}
	public String getTemplateImagePath() {
		return templateImagePath;
	}
	public void setTemplateImagePath(String templateImagePath) {
		this.templateImagePath = templateImagePath;
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
    
}