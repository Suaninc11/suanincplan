package com.suaninc.newsagency.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.suaninc.newsagency.domain.CarrierTemplate;
import com.suaninc.newsagency.domain.SalesPolicy;
import com.suaninc.newsagency.domain.TemplateCoordinate;

public interface SalesPolicyService {

    List<String> getDistinctContractPeriods();
    
    List<String> getDistinctActivationTypes();
    
    List<String> getDistinctProductNames();
    
    List<String> getDistinctPlanNames();
    
    SalesPolicy getUrlPathByConditions(SalesPolicy form);
	
}
