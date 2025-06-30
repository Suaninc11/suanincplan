package com.suaninc.newsagency.service;

import java.util.List;

import com.suaninc.newsagency.domain.SalesPolicy;

public interface SalesPolicyService {

    List<String> getDistinctContractPeriods();
    
    List<String> getDistinctActivationTypes();
    
    List<String> getDistinctProductNames();
    
    List<String> getDistinctPlanNames();
    
    List<String> getActivationTypesByContractPeriod(String contractPeriod);
    
    List<String> getProductNamesByConditions(SalesPolicy policy);
    
    List<String> getPlanNamesByConditions(SalesPolicy policy);
    
    SalesPolicy getPlanDetail(SalesPolicy form);
    
    SalesPolicy getUrlPathByConditions(SalesPolicy form);
	
}
