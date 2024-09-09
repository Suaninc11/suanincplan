package com.suaninc.newsagency.domain;

public class ApplyForm {

	private String codeDescription;
	private String customerType;
	private String templateCode; 		// 일련번호
	private String carrierPlanName;		// 일련번호
	private String mobileCarrier;		// 일련번호
	private Integer monthlyBillingAmt;	// 일련번호
	private Integer basicAmt; 			// 일련번호
	private Integer discountAmt; 		// 일련번호
	private String voiceCall;		 	// 일련번호
	private String mobileData; 			// 일련번호
	private String mobileQos; 			// 일련번호
	private String mobileMessage; 		// 일련번호

	// 요금제 관련 필드
	private String simName;	 // 모델명
    private String simSerialNumber;  // 일련번호
    private String carrierPlan;      // 요금제
    private String simCost;          // 유심 비용
    private String subscriptionCost; // 가입비

    // 고객 정보 관련 필드
    private String name;             // 가입자명
    private String birthdate;        // 생년월일
    private String address;          // 주소
    private String openingNumber;    // 개통 번호
    private String openingCategory;  // 개통 구분
    private String previousCarrier;  // 이전 통신사

    // 자동이체 관련 필드
    private String depositorName;      // 예금주명
    private String depositorBirthdate; // 예금주 생년월일
    private String paymentName;        // 은행(카드사)명
    private String effectiveDate;      // 유효기간
    private String automaticTransfer;  // 자동이체
    private String accountNumber;      // 계좌번호

    // 청소년 법정대리인 관련 필드
    private String relationship;            // 관계
    private String legalRepresentativeNumber; // 법대연락처
    private String legalRepresentativeAddress; // 법대주소

    // 부가기능 관련 필드
    private String additionalServices;  // 부가서비스
    private String overseasData;        // 해외데이터

    // 판매자 정보 관련 필드
    private String retailerName;    // 판매점 상호
    private String sellerName;      // 판매자명
    private String sellerNumber;    // 판매자 연락처
    
    private String mobileNumber;        // 가입자 연락처
    private String previousCarrierPlan; // 이동전 상품유형
    private String mobilePayment;       // 통신과금서비스
    private String carrierType;       // 통신사선택

	private String commonCheck;    // 약관 동의

	public String getCodeDescription() {
		return codeDescription;
	}

	public void setCodeDescription(String codeDescription) {
		this.codeDescription = codeDescription;
	}
	
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

    // 요금제 관련 필드
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

    public String getCarrierPlan() {
        return carrierPlan;
    }

    public void setCarrierPlan(String carrierPlan) {
        this.carrierPlan = carrierPlan;
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

    // 고객 정보 관련 필드
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

    public String getOpeningNumber() {
        return openingNumber;
    }

    public void setOpeningNumber(String openingNumber) {
        this.openingNumber = openingNumber;
    }

    public String getOpeningCategory() {
        return openingCategory;
    }

    public void setOpeningCategory(String openingCategory) {
        this.openingCategory = openingCategory;
    }

    public String getPreviousCarrier() {
        return previousCarrier;
    }

    public void setPreviousCarrier(String previousCarrier) {
        this.previousCarrier = previousCarrier;
    }

    // 자동이체 관련 필드
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

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getAutomaticTransfer() {
        return automaticTransfer;
    }

    public void setAutomaticTransfer(String automaticTransfer) {
        this.automaticTransfer = automaticTransfer;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    // 청소년 법정대리인 관련 필드
    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getLegalRepresentativeNumber() {
        return legalRepresentativeNumber;
    }

    public void setLegalRepresentativeNumber(String legalRepresentativeNumber) {
        this.legalRepresentativeNumber = legalRepresentativeNumber;
    }

    public String getLegalRepresentativeAddress() {
        return legalRepresentativeAddress;
    }

    public void setLegalRepresentativeAddress(String legalRepresentativeAddress) {
        this.legalRepresentativeAddress = legalRepresentativeAddress;
    }

    // 부가기능 관련 필드
    public String getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(String additionalServices) {
        this.additionalServices = additionalServices;
    }

    public String getOverseasData() {
        return overseasData;
    }

    public void setOverseasData(String overseasData) {
        this.overseasData = overseasData;
    }

    // 판매자 정보 관련 필드
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
    
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPreviousCarrierPlan() {
        return previousCarrierPlan;
    }

    public void setPreviousCarrierPlan(String previousCarrierPlan) {
        this.previousCarrierPlan = previousCarrierPlan;
    }

    public String getMobilePayment() {
        return mobilePayment;
    }

    public void setMobilePayment(String mobilePayment) {
        this.mobilePayment = mobilePayment;
    }
    public String getCommonCheck() {
		return commonCheck;
	}

	public void setCommonCheck(String commonCheck) {
		this.commonCheck = commonCheck;
	}
    
    public String getCarrierType() {
		return carrierType;
	}

	public void setCarrierType(String carrierType) {
		this.carrierType = carrierType;
	}
}