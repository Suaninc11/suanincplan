package com.suaninc.newsagency.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.suaninc.newsagency.domain.CarrierTemplate;
import com.suaninc.newsagency.domain.SalesPolicy;
import com.suaninc.newsagency.domain.TemplateCoordinate;

@Repository
public class SalesPolicyDaoImpl implements SalesPolicyDao {

    @Autowired
    private SqlSession sqlSession;

    private static final String NAMESPACE = "salesPolicy.";

    @Override
    public List<String> selectDistinctContractPeriods() {
        return sqlSession.selectList(NAMESPACE + "selectDistinctContractPeriods");
    }

    @Override
    public List<String> selectDistinctActivationTypes() {
        return sqlSession.selectList(NAMESPACE + "selectDistinctActivationTypes");
    }

    @Override
    public List<String> selectDistinctProductNames() {
        return sqlSession.selectList(NAMESPACE + "selectDistinctProductNames");
    }

    @Override
    public List<String> selectDistinctPlanNames() {
        return sqlSession.selectList(NAMESPACE + "selectDistinctPlanNames");
    }
    
    @Override
    public SalesPolicy selectUrlPathByConditions(SalesPolicy form) {
        return sqlSession.selectOne(NAMESPACE + "selectUrlPathByConditions", form);
    }

}
