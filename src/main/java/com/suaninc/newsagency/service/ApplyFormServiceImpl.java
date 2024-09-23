package com.suaninc.newsagency.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suaninc.newsagency.dao.ApplyFormDao;
import com.suaninc.newsagency.domain.ApplyForm;
import com.suaninc.newsagency.domain.CarrierPlan;
import com.suaninc.newsagency.domain.CarrierTemplate;
import com.suaninc.newsagency.domain.CommonCode;
import com.suaninc.newsagency.domain.TemplateCoordinate;

@Service
public class ApplyFormServiceImpl implements ApplyFormService {
	
    @Autowired
    private ApplyFormDao applyFormDao;

	@Override
	public List<CommonCode> getCarrierList() {
		return applyFormDao.selectCarrierList();
	}
	
	@Override
<<<<<<< HEAD
	public List<CarrierTemplate> getCarrierTemplate(ApplyForm form) {
		return applyFormDao.selectCarrierTemplate(form);
	}
	
	@Override
	public List<CarrierPlan> getCarrierPlan(ApplyForm form) {
		return applyFormDao.selectCarrierPlan(form);
=======
	public List<CarrierPlan> getCarrierPlanList(ApplyForm form) {
		return applyFormDao.selectCarrierPlanList(form);
	}
	
	@Override
	public List<CarrierTemplate> getCarrierTemplate(ApplyForm form) {
		return applyFormDao.selectCarrierTemplate(form);
>>>>>>> 6d971a4cc491035c9e57842d3fa3e8643b8f5651
	}

	@Override
	public List<TemplateCoordinate> getTemplateCoordinateList(CarrierTemplate templateImageOrder) {
		return applyFormDao.selectTemplateCoordinateList(templateImageOrder);
	}

}
