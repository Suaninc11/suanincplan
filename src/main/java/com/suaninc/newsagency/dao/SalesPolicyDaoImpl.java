package com.suaninc.newsagency.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suaninc.newsagency.domain.SalesPolicy;

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
    public List<String> selectActivationTypesByContractPeriod(String contractPeriod) {
        return sqlSession.selectList(NAMESPACE + "selectActivationTypesByContractPeriod", contractPeriod);
    }

    @Override
    public List<String> selectProductNamesByConditions(SalesPolicy form) {
        return sqlSession.selectList(NAMESPACE + "selectProductNamesByConditions", form);
    }

    @Override
    public List<String> selectPlanNamesByConditions(SalesPolicy form) {
        return sqlSession.selectList(NAMESPACE + "selectPlanNamesByConditions", form);
    }
    
    @Override
    public SalesPolicy selectPlanDetail(SalesPolicy form) {
        return sqlSession.selectOne(NAMESPACE + "selectPlanDetail", form);
    }

    @Override
    public SalesPolicy selectUrlPathByConditions(SalesPolicy form) {
        return sqlSession.selectOne(NAMESPACE + "selectUrlPathByConditions", form);
    }

}
