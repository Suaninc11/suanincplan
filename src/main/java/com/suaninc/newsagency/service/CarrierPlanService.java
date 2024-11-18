package com.suaninc.newsagency.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.suaninc.newsagency.domain.CarrierPlan;

public interface CarrierPlanService {

	// 요금제 목록
	public Page<CarrierPlan> getCarrierPlanList(CarrierPlan form, Pageable pageable);
	
	// 요금제 상세
	public CarrierPlan getCarrierPlanInfo(Long carrierPlanId);
	
	// 요금제 수정
	public int modifyCarrierPlan(CarrierPlan form);
	
	// 요금제 삭제
	public int deleteCarrierPlan(CarrierPlan form);
	
	// 요금제 작성
	public int createCarrierPlan(CarrierPlan form);

	
}
