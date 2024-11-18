package com.suaninc.newsagency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.suaninc.newsagency.dao.CarrierPlanDao;
import com.suaninc.newsagency.domain.CarrierPlan;

@Service
public class CarrierPlanServiceImpl implements CarrierPlanService {
	
    @Autowired
    private CarrierPlanDao carrierPlanDao;

	@Override
	public Page<CarrierPlan> getCarrierPlanList(CarrierPlan form, Pageable pageable) {
		return carrierPlanDao.selectCarrierPlanList(form, pageable);
	}
	
	@Override
	public CarrierPlan getCarrierPlanInfo(Long carrierPlanId) {
		return carrierPlanDao.selectCarrierPlanInfo(carrierPlanId);
	}
	
	@Override
	public int modifyCarrierPlan(CarrierPlan form) {
		return carrierPlanDao.modifyCarrierPlan(form);
	}

	@Override
	public int deleteCarrierPlan(CarrierPlan form) {
		return carrierPlanDao.deleteCarrierPlan(form);
	}
	
	@Override
	public int createCarrierPlan(CarrierPlan form) {
		return carrierPlanDao.createCarrierPlan(form);
	}
	
	
}
