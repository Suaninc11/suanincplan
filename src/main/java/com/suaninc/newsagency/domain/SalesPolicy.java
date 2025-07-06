package com.suaninc.newsagency.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalesPolicy {
	
	private Integer id;
    private String salesPolicyName;
    private String agencyName;
    private String startDatetime;
    private String endDatetime;
    private String productCode;
    private String productName;
    private String isUsedProduct;
    private String planName;
    private String contractPeriod;
    private String activationType;
    private String subsidyType;
    private Integer baseAmt;
    private Integer discountAmt;
    private Integer additionalDiscountAmt;
    private Integer deviceAmt;
    private Integer officialSubsidy;
    private Integer agencySubsidy;
    private Integer installmentPrincipal;
    private Integer installmentFee;
    private Integer newJoinCommission;
    private Integer movingCommission;
    private Integer changeCommission;
    private String classification;
    private String urlPath;
    private String mobileUrlPath;
    private String lastUpdatedDatetime;
    private String registeredDatetime;
    
    private String installmentType; // 할부 유형 (LIKE 검색용)
    
    private Integer installmentAmount;       // 할부금 (계산값)
    private Integer installmentInterest;     // 할부이자 (계산값)
    private Integer monthlyBill;             // 월 청구요금 (계산값)
	
}