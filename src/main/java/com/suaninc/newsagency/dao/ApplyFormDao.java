package com.suaninc.newsagency.dao;

import java.util.List;

import com.suaninc.newsagency.domain.ApplyForm;
import com.suaninc.newsagency.domain.CarrierPlan;
import com.suaninc.newsagency.domain.CarrierTemplate;
import com.suaninc.newsagency.domain.CommonCode;
import com.suaninc.newsagency.domain.TemplateCoordinate;

public interface ApplyFormDao {

	public List<CommonCode> selectCarrierList();
	
<<<<<<< HEAD
	public List<CarrierTemplate> selectCarrierTemplate(ApplyForm form);
	
	public List<CarrierPlan> selectCarrierPlan(ApplyForm form);
=======
	public List<CarrierPlan> selectCarrierPlanList(ApplyForm form);
	
	public List<CarrierTemplate> selectCarrierTemplate(ApplyForm form);
>>>>>>> 6d971a4cc491035c9e57842d3fa3e8643b8f5651

	public List<TemplateCoordinate> selectTemplateCoordinateList(CarrierTemplate templateImageOrder);

}
