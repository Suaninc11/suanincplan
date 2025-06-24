package com.suaninc.newsagency.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.suaninc.newsagency.domain.CarrierTemplate;
import com.suaninc.newsagency.domain.SalesPolicy;
import com.suaninc.newsagency.domain.TemplateCoordinate;

public interface SalesPolicyDao {

	List<String> selectDistinctContractPeriods();
	
    List<String> selectDistinctActivationTypes();
    
    List<String> selectDistinctProductNames();
    
    List<String> selectDistinctPlanNames();
    
    SalesPolicy selectUrlPathByConditions(SalesPolicy form);

}
