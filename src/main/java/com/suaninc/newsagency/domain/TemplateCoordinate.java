package com.suaninc.newsagency.domain;

import java.time.LocalDateTime;

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
    private LocalDateTime lastUpdatedDatetime;
    private LocalDateTime registeredDatetime;
    
    private String templateImagePath;
    
    private Integer customerTypeCount;			// 고객구분
    private Integer customerInfoCount;			// 고객정보
    private Integer mobileCarrierCount;			// 요금제
    private Integer simInfoCount;				// 가입비 및 유심정보
    private Integer paymentInfoCount;			// 결제정보
    private Integer legalRepresentativeCount;	// 법정대리인
    private Integer additionalFunctionCount;	// 부가기능
    private Integer sellerInfoCount;			// 판매자정보
    private Integer commonInfoCount;			// 공통체크
    
}
