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

<<<<<<< HEAD
	public List<CarrierPlan> getCarrierPlan(ApplyForm form);
	
=======
	public List<CarrierPlan> getCarrierPlanList(ApplyForm form);

>>>>>>> 6d971a4cc491035c9e57842d3fa3e8643b8f5651
	public List<TemplateCoordinate> getTemplateCoordinateList(CarrierTemplate templateImageOrder);
	
}
