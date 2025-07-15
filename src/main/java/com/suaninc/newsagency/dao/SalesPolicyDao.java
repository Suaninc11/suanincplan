package com.suaninc.newsagency.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.suaninc.newsagency.domain.SalesPolicy;

public interface SalesPolicyDao {

    List<String> selectDistinctContractPeriods();

    List<String> selectDistinctActivationTypes();

    List<String> selectDistinctProductNames();

    List<String> selectDistinctPlanNames();

    List<String> selectActivationTypesByContractPeriod(String contractPeriod);

    List<String> selectProductNamesByConditions(SalesPolicy form);

    List<String> selectPlanNamesByConditions(SalesPolicy form);

    SalesPolicy selectPlanDetail(SalesPolicy form);

    SalesPolicy selectUrlPathByConditions(SalesPolicy form);
    
	Page<SalesPolicy> selectSalesPolicyList(SalesPolicy form, Pageable pageable);

	void insertBulkSalesPolicy(List<SalesPolicy> policyList);
	
	SalesPolicy selectSalePolicyInfo(String id);
	
}
