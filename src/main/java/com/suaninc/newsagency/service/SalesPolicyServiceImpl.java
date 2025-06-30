package com.suaninc.newsagency.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suaninc.newsagency.dao.SalesPolicyDao;
import com.suaninc.newsagency.domain.SalesPolicy;

@Service
public class SalesPolicyServiceImpl implements SalesPolicyService {
	
    @Autowired
    private SalesPolicyDao salesPolicyDao;

    @Override
    public List<String> getDistinctContractPeriods() {
        return salesPolicyDao.selectDistinctContractPeriods();
    }

    @Override
    public List<String> getDistinctActivationTypes() {
        return salesPolicyDao.selectDistinctActivationTypes();
    }

    @Override
    public List<String> getDistinctProductNames() {
        return salesPolicyDao.selectDistinctProductNames();
    }

    @Override
    public List<String> getDistinctPlanNames() {
        return salesPolicyDao.selectDistinctPlanNames();
    }

    @Override
    public List<String> getActivationTypesByContractPeriod(String contractPeriod) {
        return salesPolicyDao.selectActivationTypesByContractPeriod(contractPeriod);
    }

    @Override
    public List<String> getProductNamesByConditions(SalesPolicy form) {
        return salesPolicyDao.selectProductNamesByConditions(form);
    }

    @Override
    public List<String> getPlanNamesByConditions(SalesPolicy form) {
        return salesPolicyDao.selectPlanNamesByConditions(form);
    }
    
    @Override
    public SalesPolicy getPlanDetail(SalesPolicy form) {
        return salesPolicyDao.selectPlanDetail(form);
    }

    @Override
    public SalesPolicy getUrlPathByConditions(SalesPolicy form) {
        return salesPolicyDao.selectUrlPathByConditions(form);
    }
    
}
