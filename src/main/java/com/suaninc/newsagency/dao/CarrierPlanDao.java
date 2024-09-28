package com.suaninc.newsagency.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.suaninc.newsagency.domain.CarrierPlan;

public interface CarrierPlanDao {

	public Page<CarrierPlan> selectCarrierPlanList(CarrierPlan form, Pageable pageable);
	
	public CarrierPlan selectCarrierPlanInfo(Long carrierPlanId);
	
	public int modifyCarrierPlan(CarrierPlan form);

	public int deleteCarrierPlan(CarrierPlan form);

	public int addCarrierPlan(CarrierPlan form);

}
