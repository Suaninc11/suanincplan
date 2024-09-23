package com.suaninc.newsagency.dao;

import java.util.List;

import com.suaninc.newsagency.domain.ApplyForm;
import com.suaninc.newsagency.domain.CarrierPlan;
import com.suaninc.newsagency.domain.CarrierTemplate;
import com.suaninc.newsagency.domain.CommonCode;
import com.suaninc.newsagency.domain.TemplateCoordinate;

public interface ApplyFormDao {

	public List<CommonCode> selectCarrierList();
	
	public List<CarrierTemplate> selectCarrierTemplate(ApplyForm form);
	
	public List<CarrierPlan> selectCarrierPlan(ApplyForm form);

	public List<TemplateCoordinate> selectTemplateCoordinateList(CarrierTemplate templateImageOrder);

}
