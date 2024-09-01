package com.suaninc.newsagency.model;

public class TemplateCoordinate {
	
    private String emplateCode;
    private String templateImageOrder;
    private String templateCoordinateName;
    private Integer coordinateXAxis;
    private Integer coordinateYAxis;
    private String registeredDatetime;
    
	public String getEmplateCode() {
		return emplateCode;
	}
	public void setEmplateCode(String emplateCode) {
		this.emplateCode = emplateCode;
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
	public String getRegisteredDatetime() {
		return registeredDatetime;
	}
	public void setRegisteredDatetime(String registeredDatetime) {
		this.registeredDatetime = registeredDatetime;
	}
    
}
