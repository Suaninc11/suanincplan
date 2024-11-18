package com.suaninc.newsagency.domain;

import java.util.Date;

public class TemplateCoordinate {
	
	private Integer templateCoordinateId;
	private String templateCode;
	private String templateType;
    private String templateImageOrder;
    private String templateCoordinateName;
    private String templateInputType;
    private String templateInputOption;
    private String fontStyle;
    private Integer fontSize;
    private Integer coordinateXAxis;
    private Integer coordinateYAxis;
    private Date lastUpdatedDatetime;
    private Date registeredDatetime;
    
    private String templateImagePath; 

	public Integer getTemplateCoordinateId() {
		return templateCoordinateId;
	}
	public void setTemplateCoordinateId(Integer templateCoordinateId) {
		this.templateCoordinateId = templateCoordinateId;
	}
	public String getTemplateCode() {
		return templateCode;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	public String getTemplateType() {
		return templateType;
	}
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
	public String getTemplateImageOrder() {
		return templateImageOrder;
	}
	public void setTemplateImageOrder(String templateImageOrder) {
		this.templateImageOrder = templateImageOrder;
	}
	public String getTemplateCoordinateName() {
		return templateCoordinateName;
	}
	public void setTemplateCoordinateName(String templateCoordinateName) {
		this.templateCoordinateName = templateCoordinateName;
	}
	public String getTemplateInputType() {
		return templateInputType;
	}
	public void setTemplateInputType(String templateInputType) {
		this.templateInputType = templateInputType;
	}
	public String getTemplateInputOption() {
		return templateInputOption;
	}
	public void setTemplateInputOption(String templateInputOption) {
		this.templateInputOption = templateInputOption;
	}
	public String getFontStyle() {
		return fontStyle;
	}
	public void setFontStyle(String fontStyle) {
		this.fontStyle = fontStyle;
	}
	public Integer getFontSize() {
		return fontSize;
	}
	public void setFontSize(Integer fontSize) {
		this.fontSize = fontSize;
	}
	public Integer getCoordinateXAxis() {
		return coordinateXAxis;
	}
	public void setCoordinateXAxis(Integer coordinateXAxis) {
		this.coordinateXAxis = coordinateXAxis;
	}
	public Integer getCoordinateYAxis() {
		return coordinateYAxis;
	}
	public void setCoordinateYAxis(Integer coordinateYAxis) {
		this.coordinateYAxis = coordinateYAxis;
	}
	public Date getLastUpdatedDatetime() {
		return lastUpdatedDatetime;
	}
	public void setLastUpdatedDatetime(Date lastUpdatedDatetime) {
		this.lastUpdatedDatetime = lastUpdatedDatetime;
	}
	public Date getRegisteredDatetime() {
		return registeredDatetime;
	}
	public void setRegisteredDatetime(Date registeredDatetime) {
		this.registeredDatetime = registeredDatetime;
	}
	public String getTemplateImagePath() {
		return templateImagePath;
	}
	public void setTemplateImagePath(String templateImagePath) {
		this.templateImagePath = templateImagePath;
	}
    
}
