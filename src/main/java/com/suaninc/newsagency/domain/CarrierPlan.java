package com.suaninc.newsagency.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
    private String activeYn;
    private LocalDateTime lastUpdatedDatetime;
    private LocalDateTime registeredDatetime;
    
    private Long offset;
    private Integer pageSize;
    
}
