package com.suaninc.newsagency.domain;

public class ApplyForm {

	// 고객구분 관련 필드
	private String customerType;
	
	// 요금제 관련 필드
	private String templateCode; 		// 일련번호
	private String carrierType;
	private String carrierPlan;      	// 요금제
	private String carrierPlanName;		// 요금제명
	private String mobileCarrier;		// 일련번호
	private Integer monthlyBillingAmt;	// 월 청구요금
	private Integer basicAmt; 			// 기본료
	private Integer discountAmt; 		// 할인
	private String voiceCall;		 	// 일련번호
	private String mobileData; 			// 일련번호
	private String mobileQos; 			// 일련번호
	private String mobileMessage; 		// 일련번호
    
	// 가입비 및 유심정보 관련 필드
	private String simName;	 			// 모델명
    private String simSerialNumber;  	// 일련번호
    private String simCost;          	// 유심 비용
    private String subscriptionCost; 	// 가입비

    // 고객정보 관련 필드
    private String openingCategory;  	// 개통구분
    private String name;            	// 가입자명
    private String birthdate;        	// 생년월일
    private String address;          	// 주소
    private String activationNumber;	// 개통 번호
    private String portabilityNumber;	// 개통 번호
    private String mobileNumber;        // 가입자 번호
    private String previousCarrier;  	// 이전 통신사
    private String commonMoveCheck; 	// 번호 이동 기본 체크
    private String mvno;				// MVNO

    // 자동이체 관련 필드
    private String automaticTransfer;  	// 결제방식
    private String cardName;  			// 예금주명
    private String cardBirthdate;  		// 생년월일
    private String cardCompany;  		// 카드사명
    private String cardRelationship;	// 관계
    private String cardNumber;  		// 카드번호
    private String cardYear;  			// 유효기간(년)
    private String cardMonth;  			// 유효기간(월)
    private String accountName;  		// 예금주명
    private String accountBirthdate;  	// 생년월일
    private String accountBank;  		// 은행명
    private String accountNumber;  		// 계좌번호

    // 법정대리인 관련 필드
    private String depositorName;		// 법정대리인명
    private String depositorBirthdate;	// 생년월일
    private String depositorNumber; 	// 법대연락처
    private String relationship;        // 관계

    // 부가기능 관련 필드
    private String mobilePayment;  		// 통신과금서비스
    private String overseasData;        // 해외데이터

    // 판매자정보 관련 필드
    private String retailerName;    	// 판매점 상호
    private String sellerName;      	// 판매자명
    private String sellerNumber;    	// 판매자 연락처
    
    // 공통체크
	private String commonCheck;   		// 약관 동의
	private String commonText;   		// 약관 동의
	private String commonYear;    		// 약관 년도
	private String commonShortYear;    	// 약관 년도 뒷자리
	private String commonMonth;    		// 약관 월
	private String commonDay;    		// 약관 일
	
	
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getTemplateCode() {
		return templateCode;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	public String getCarrierType() {
		return carrierType;
	}
	public void setCarrierType(String carrierType) {
		this.carrierType = carrierType;
	}
	public String getCarrierPlan() {
		return carrierPlan;
	}
	public void setCarrierPlan(String carrierPlan) {
		this.carrierPlan = carrierPlan;
	}
	public String getCarrierPlanName() {
		return carrierPlanName;
	}
	public void setCarrierPlanName(String carrierPlanName) {
		this.carrierPlanName = carrierPlanName;
	}
	public String getMobileCarrier() {
		return mobileCarrier;
	}
	public void setMobileCarrier(String mobileCarrier) {
		this.mobileCarrier = mobileCarrier;
	}
	public Integer getMonthlyBillingAmt() {
		return monthlyBillingAmt;
	}
	public void setMonthlyBillingAmt(Integer monthlyBillingAmt) {
		this.monthlyBillingAmt = monthlyBillingAmt;
	}
	public Integer getBasicAmt() {
		return basicAmt;
	}
	public void setBasicAmt(Integer basicAmt) {
		this.basicAmt = basicAmt;
	}
	public Integer getDiscountAmt() {
		return discountAmt;
	}
	public void setDiscountAmt(Integer discountAmt) {
		this.discountAmt = discountAmt;
	}
	public String getVoiceCall() {
		return voiceCall;
	}
	public void setVoiceCall(String voiceCall) {
		this.voiceCall = voiceCall;
	}
	public String getMobileData() {
		return mobileData;
	}
	public void setMobileData(String mobileData) {
		this.mobileData = mobileData;
	}
	public String getMobileQos() {
		return mobileQos;
	}
	public void setMobileQos(String mobileQos) {
		this.mobileQos = mobileQos;
	}
	public String getMobileMessage() {
		return mobileMessage;
	}
	public void setMobileMessage(String mobileMessage) {
		this.mobileMessage = mobileMessage;
	}
	public String getSimName() {
		return simName;
	}
	public void setSimName(String simName) {
		this.simName = simName;
	}
	public String getSimSerialNumber() {
		return simSerialNumber;
	}
	public void setSimSerialNumber(String simSerialNumber) {
		this.simSerialNumber = simSerialNumber;
	}
	public String getSimCost() {
		return simCost;
	}
	public void setSimCost(String simCost) {
		this.simCost = simCost;
	}
	public String getSubscriptionCost() {
		return subscriptionCost;
	}
	public void setSubscriptionCost(String subscriptionCost) {
		this.subscriptionCost = subscriptionCost;
	}
	public String getOpeningCategory() {
		return openingCategory;
	}
	public void setOpeningCategory(String openingCategory) {
		this.openingCategory = openingCategory;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getActivationNumber() {
		return activationNumber;
	}
	public void setActivationNumber(String activationNumber) {
		this.activationNumber = activationNumber;
	}
	public String getPortabilityNumber() {
		return portabilityNumber;
	}
	public void setPortabilityNumber(String portabilityNumber) {
		this.portabilityNumber = portabilityNumber;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPreviousCarrier() {
		return previousCarrier;
	}
	public void setPreviousCarrier(String previousCarrier) {
		this.previousCarrier = previousCarrier;
	}
	public String getCommonMoveCheck() {
		return commonMoveCheck;
	}
	public void setCommonMoveCheck(String commonMoveCheck) {
		this.commonMoveCheck = commonMoveCheck;
	}
	public String getMvno() {
		return mvno;
	}
	public void setMvno(String mvno) {
		this.mvno = mvno;
	}
	public String getAutomaticTransfer() {
		return automaticTransfer;
	}
	public void setAutomaticTransfer(String automaticTransfer) {
		this.automaticTransfer = automaticTransfer;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getCardBirthdate() {
		return cardBirthdate;
	}
	public void setCardBirthdate(String cardBirthdate) {
		this.cardBirthdate = cardBirthdate;
	}
	public String getCardCompany() {
		return cardCompany;
	}
	public void setCardCompany(String cardCompany) {
		this.cardCompany = cardCompany;
	}
	public String getCardRelationship() {
		return cardRelationship;
	}
	public void setCardRelationship(String cardRelationship) {
		this.cardRelationship = cardRelationship;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardYear() {
		return cardYear;
	}
	public void setCardYear(String cardYear) {
		this.cardYear = cardYear;
	}
	public String getCardMonth() {
		return cardMonth;
	}
	public void setCardMonth(String cardMonth) {
		this.cardMonth = cardMonth;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountBirthdate() {
		return accountBirthdate;
	}
	public void setAccountBirthdate(String accountBirthdate) {
		this.accountBirthdate = accountBirthdate;
	}
	public String getAccountBank() {
		return accountBank;
	}
	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getDepositorName() {
		return depositorName;
	}
	public void setDepositorName(String depositorName) {
		this.depositorName = depositorName;
	}
	public String getDepositorBirthdate() {
		return depositorBirthdate;
	}
	public void setDepositorBirthdate(String depositorBirthdate) {
		this.depositorBirthdate = depositorBirthdate;
	}
	public String getDepositorNumber() {
		return depositorNumber;
	}
	public void setDepositorNumber(String depositorNumber) {
		this.depositorNumber = depositorNumber;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	public String getMobilePayment() {
		return mobilePayment;
	}
	public void setMobilePayment(String mobilePayment) {
		this.mobilePayment = mobilePayment;
	}
	public String getOverseasData() {
		return overseasData;
	}
	public void setOverseasData(String overseasData) {
		this.overseasData = overseasData;
	}
	public String getRetailerName() {
		return retailerName;
	}
	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getSellerNumber() {
		return sellerNumber;
	}
	public void setSellerNumber(String sellerNumber) {
		this.sellerNumber = sellerNumber;
	}
	public String getCommonCheck() {
		return commonCheck;
	}
	public void setCommonCheck(String commonCheck) {
		this.commonCheck = commonCheck;
	}
	public String getCommonText() {
		return commonText;
	}
	public void setCommonText(String commonText) {
		this.commonText = commonText;
	}
	public String getCommonYear() {
		return commonYear;
	}
	public void setCommonYear(String commonYear) {
		this.commonYear = commonYear;
	}
	public String getCommonShortYear() {
		return commonShortYear;
	}
	public void setCommonShortYear(String commonShortYear) {
		this.commonShortYear = commonShortYear;
	}
	public String getCommonMonth() {
		return commonMonth;
	}
	public void setCommonMonth(String commonMonth) {
		this.commonMonth = commonMonth;
	}
	public String getCommonDay() {
		return commonDay;
	}
	public void setCommonDay(String commonDay) {
		this.commonDay = commonDay;
	}
	
}