package com.suaninc.newsagency.domain;

import java.util.Date;

public class CarrierPlan {

    private Integer carrierPlanId;
    private String templateCode;
    private String carrierPlanName;
    private String mobileCarrier;
    private Integer monthlyBillingAmt;
    private Integer basicAmt;
    private Integer discountAmt;
    private String voiceCall;
    private String mobileData;
    private String mobileQos;
    private String mobileMessage;
    private Date registeredDatetime;
    
    private Long offset;
    private Integer pageSize;
    
	public int getCarrierPlanId() {
		return carrierPlanId;
	}
	public void setCarrierPlanId(Integer carrierPlanId) {
		this.carrierPlanId = carrierPlanId;
	}
	public String getTemplateCode() {
		return templateCode;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	public String getCarrierPlanName() {
		return carrierPlanName;
	}
	public void setCarrierPlanName(String carrierPlanName) {
		this.carrierPlanName = carrierPlanName;
	}
	public String getMobileCarrier() {
		return mobileCarrier;
	}
	public void setMobileCarrier(String mobileCarrier) {
		this.mobileCarrier = mobileCarrier;
	}
	public Integer getMonthlyBillingAmt() {
		return monthlyBillingAmt;
	}
	public void setMonthlyBillingAmt(Integer monthlyBillingAmt) {
		this.monthlyBillingAmt = monthlyBillingAmt;
	}
	public Integer getBasicAmt() {
		return basicAmt;
	}
	public void setBasicAmt(Integer basicAmt) {
		this.basicAmt = basicAmt;
	}
	public Integer getDiscountAmt() {
		return discountAmt;
	}
	public void setDiscountAmt(Integer discountAmt) {
		this.discountAmt = discountAmt;
	}
	public String getVoiceCall() {
		return voiceCall;
	}
	public void setVoiceCall(String voiceCall) {
		this.voiceCall = voiceCall;
	}
	public String getMobileData() {
		return mobileData;
	}
	public void setMobileData(String mobileData) {
		this.mobileData = mobileData;
	}
	public String getMobileQos() {
		return mobileQos;
	}
	public void setMobileQos(String mobileQos) {
		this.mobileQos = mobileQos;
	}
	public String getMobileMessage() {
		return mobileMessage;
	}
	public void setMobileMessage(String mobileMessage) {
		this.mobileMessage = mobileMessage;
	}
	public Date getRegisteredDatetime() {
		return registeredDatetime;
	}
	public void setRegisteredDatetime(Date registeredDatetime) {
		this.registeredDatetime = registeredDatetime;
	}
	
	public Long getOffset() {
		return offset;
	}
	public void setOffset(Long offset) {
		this.offset = offset;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
