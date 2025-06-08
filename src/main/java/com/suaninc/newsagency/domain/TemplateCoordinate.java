package com.suaninc.newsagency.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
    private String lastUpdatedDatetime;
    private String registeredDatetime;
    
    private String templateImagePath; 
    
}
