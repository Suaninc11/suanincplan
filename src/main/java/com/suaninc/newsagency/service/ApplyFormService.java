package com.suaninc.newsagency.service;

import java.util.List;

import com.suaninc.newsagency.domain.ApplyForm;
import com.suaninc.newsagency.domain.CarrierPlan;
import com.suaninc.newsagency.domain.CarrierTemplate;
import com.suaninc.newsagency.domain.CommonCode;
import com.suaninc.newsagency.domain.TemplateCoordinate;

public interface ApplyFormService {

    public List<CommonCode> getCarrierList();
    
    public List<CarrierTemplate> getCarrierTemplate(ApplyForm form);

	public List<CarrierPlan> getCarrierPlanList(ApplyForm form);

	public List<TemplateCoordinate> getTemplateCoordinateList(CarrierTemplate templateImageOrder);
	
}
