package com.suaninc.newsagency.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.suaninc.newsagency.dao.SalesPolicyDao;
import com.suaninc.newsagency.dao.TemplateDao;
import com.suaninc.newsagency.domain.CarrierTemplate;
import com.suaninc.newsagency.domain.SalesPolicy;
import com.suaninc.newsagency.domain.TemplateCoordinate;

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
    public SalesPolicy getUrlPathByConditions(SalesPolicy form) {
        return salesPolicyDao.selectUrlPathByConditions(form);
    }
    
}
