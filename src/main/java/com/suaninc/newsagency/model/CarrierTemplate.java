package com.suaninc.newsagency.model;

public class CarrierTemplate {
	
	private Integer carrierTemplateId;
    private String templateCode;
    private Integer templateImageOrder;
    private String templateImageName;
    private String registeredDatetime;
    
	public Integer getCarrierTemplateId() {
		return carrierTemplateId;
	}
	public void setCarrierTemplateId(Integer carrierTemplateId) {
		this.carrierTemplateId = carrierTemplateId;
	}
	public String getRegisteredDatetime() {
		return registeredDatetime;
	}
	public void setRegisteredDatetime(String registeredDatetime) {
		this.registeredDatetime = registeredDatetime;
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
	public String getTemplateImageName() {
		return templateImageName;
	}
	public void setTemplateImageName(String templateImageName) {
		this.templateImageName = templateImageName;
	}

}