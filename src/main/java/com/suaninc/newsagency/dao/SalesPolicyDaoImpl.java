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

	@Override
	public Page<SalesPolicy> selectSalesPolicyList(SalesPolicy form, Pageable pageable) {
	    // 전체 레코드 수 가져오기
	    int total = sqlSession.selectOne(NAMESPACE + "selectSalesPolicyListCount", form);
	    
	    // 페이징을 위한 파라미터 설정 (Map으로 묶음)
	    Map<String, Object> params = new HashMap<>();
	    params.put("form", form);
	    params.put("offset", pageable.getOffset());
	    params.put("pageSize", pageable.getPageSize());
	    
	    // 페이징된 리스트 가져오기
	    List<SalesPolicy> salesPolicyList = sqlSession.selectList(NAMESPACE + "selectSalesPolicyList", params);
	    
	    // Page 객체로 변환하여 반환
	    return new PageImpl<>(salesPolicyList, pageable, total);
	}
	
	@Override
    public void insertBulkSalesPolicy(List<SalesPolicy> policyList) {
        for (SalesPolicy policy : policyList) {
            sqlSession.insert(NAMESPACE + "insertSalesPolicy", policy);
        }
    }
	
	@Override
	public SalesPolicy selectSalePolicyInfo(String id) {
		return sqlSession.selectOne(NAMESPACE + "selectSalePolicyInfo", id);
	}

}
