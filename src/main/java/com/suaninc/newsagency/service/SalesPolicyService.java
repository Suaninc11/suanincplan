package com.suaninc.newsagency.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

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
    
    Page<SalesPolicy> getSalesPolicyList(SalesPolicy form, Pageable pageable);
	
    void uploadSalesPolicyExcel(MultipartFile file) throws IOException;
    
	// 템플릿 상세
    SalesPolicy getSalePolicyInfo(String id);

    
}
