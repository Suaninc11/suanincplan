package com.suaninc.newsagency.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
	
}