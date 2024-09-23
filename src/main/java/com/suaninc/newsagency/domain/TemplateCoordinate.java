package com.suaninc.newsagency.domain;

import java.util.Date;

public class TemplateCoordinate {
	
	private Integer templateCoordinateId;
	private String templateCode;
    private String templateImageOrder;
    private String templateCoordinateName;
    private String templateInputType;
    private String templateInputOption;
    private String fontStyle;
    private String fontSize;
    private Integer coordinateXAxis;
    private Integer coordinateYAxis;
    private Date registeredDatetime;

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
	public String getFontSize() {
		return fontSize;
	}
	public void setFontSize(String fontSize) {
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
	public Date getRegisteredDatetime() {
		return registeredDatetime;
	}
	public void setRegisteredDatetime(Date registeredDatetime) {
		this.registeredDatetime = registeredDatetime;
	}
    
}
