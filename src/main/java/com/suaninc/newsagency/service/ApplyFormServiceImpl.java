package com.suaninc.newsagency.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suaninc.newsagency.dao.ApplyFormDao;
import com.suaninc.newsagency.domain.ApplyForm;
import com.suaninc.newsagency.domain.CarrierPlan;
import com.suaninc.newsagency.domain.CarrierTemplate;
import com.suaninc.newsagency.domain.TemplateCoordinate;

@Service
public class ApplyFormServiceImpl implements ApplyFormService {
	
    @Autowired
    private ApplyFormDao applyFormDao;

	@Override
	public List<CarrierTemplate> getCarrierTemplate(ApplyForm form) {
		return applyFormDao.selectCarrierTemplate(form);
	}
	
	@Override
	public List<CarrierPlan> getCarrierPlan(String templateCode) {
		return applyFormDao.selectCarrierPlan(templateCode);
	}

	@Override
	public List<TemplateCoordinate> getTemplateCoordinateList(CarrierTemplate templateImageOrder) {
		return applyFormDao.selectTemplateCoordinateList(templateImageOrder);
	}

}
