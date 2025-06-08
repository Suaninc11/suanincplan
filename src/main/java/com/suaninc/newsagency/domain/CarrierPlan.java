package com.suaninc.newsagency.domain;

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
    private Integer carrierPlanOrder;
    private String activeYn;
    private String lastUpdatedDatetime;
    private String registeredDatetime;
    
    private Long offset;
    private Integer pageSize;
    
}
