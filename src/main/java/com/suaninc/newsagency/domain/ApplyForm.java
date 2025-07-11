package com.suaninc.newsagency.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplyForm {

	// 고객구분 관련 필드
	private String serviceType;		// 서비스구분
	private String walkInCustomerType;		// 고객구분
	private String workType;		// 업무구분
	private String customerType;		// 고객구분
	
	// 요금제 관련 필드
	private String templateCode; 		// 통신사코드
	private String carrierType;			// 통신사종류
	private String carrierPlan;      	// 요금제
	private String carrierPlanName;		// 통신사종류
	private String mobileCarrier;		// 요금제
	private Integer monthlyBillingAmt;	// 월청구요금
	private Integer basicAmt; 			// 기본료
	private Integer discountAmt; 		// 할인
	private String voiceCall;		 	// 음성
	private String mobileData; 			// 데이터
	private String mobileQos; 			// QOS
	private String mobileMessage; 		// 메시지
    
	// 가입비 및 유심정보 관련 필드
	private String simName;	 			// 모델명
    private String simSerialNumber;  	// 일련번호
    private String simCost;          	// 유심비용
    private String simCostValue;        // 유심고정비용
    private String subscriptionCost; 	// 가입비

    // 고객정보 관련 필드
    private String openingCategory;  	// 개통구분
    private String name;            	// 가입자명
    private String birthdate;        	// 생년월일
    private String address;          	// 주소
    private String activationNumber;	// 개통번호
    private String portabilityNumber;	// 이동번호
    private String mobileNumber;        // 가입자번호
    private String gender;        		// 성별
    private String previousCarrier;  	// 이전통신사
    private String commonMoveCheck; 	// 번호이동 기본체크
    private String mvno;				// MVNO
    
    private String skTelinkNumber;		// skTelink 가입자 or 번호이동 번호

    // 자동이체 관련 필드
    private String automaticTransfer;  	// 결제방식
    private String cardName;  			// 예금주명
    private String cardBirthdate;  		// 생년월일
    private String cardAddress;			// 청구주소
    private String cardCompany;  		// 카드사명
    private String cardRelationship;	// 관계
    private String cardNumber;  		// 카드번호
    private String cardYear;  			// 유효기간(년)
    private String cardMonth;  			// 유효기간(월)
    private String accountName;  		// 예금주명
    private String accountBirthdate;  	// 생년월일
    private String accountMobileNumber; // 예굼주 전화번호
    private String accountAddress;		// 청구주소
    private String accountBank;  		// 은행명
    private String accountNumber;  		// 계좌번호

    // 법정대리인 관련 필드
    private String depositorName;		// 법정대리인명
    private String depositorBirthdate;	// 생년월일
    private String depositorNumber; 	// 법대연락처
    private String depositorGender;     // 법대성별
    private String relationship;        // 관계
    private String relationshipOption;  // 관계
    private String depositorCheck;  // 법정대리인 체크

    // 부가기능 관련 필드
    private String mobilePayment;  		// 통신과금서비스
    private String overseasData;        // 해외데이터
    private String combination;         // 아무나 결합
    private String catchCallPlusName;	// 캐치콜플러스
    private Integer catchCallPlusAmt;	// 캐치콜플러스 금액

    // 판매자정보 관련 필드
    private String retailerName;    	// 판매점 상호
    private String sellerName;      	// 판매자명
    private String sellerNumber;    	// 판매자 연락처
    
    // 공통체크
	private String commonCheck;   		// 공통체크
	private String commonText;   		// 공통문구
	private String commonYear;    		// 약관년도
	private String commonShortYear;    	// 약관년도 뒷자리
	private String commonShortShortYear;// 약관년도 뒷한자리
	private String commonMonth;    		// 약관월
	private String commonDay;    		// 약관일
	
	private String penalty3Months;    		// 약관년도
	private String penalty9Months;    	// 약관년도 뒷자리
	private String discount12Months;// 약관년도 뒷한자리
	private String discount12MonthsCheck;    		// 약관월
	private String label12Months;    		// 약관일
	
}