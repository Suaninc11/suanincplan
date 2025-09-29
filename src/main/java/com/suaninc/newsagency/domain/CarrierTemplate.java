package com.suaninc.newsagency.domain;

import java.time.LocalDateTime;

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
    private LocalDateTime lastUpdatedDatetime;
    private LocalDateTime registeredDatetime;
    
    private String templateCount;
    private Integer rowNum;
	
}