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

import com.suaninc.newsagency.domain.CarrierPlan;

@Repository
public class CarrierPlanDaoImpl implements CarrierPlanDao {

    @Autowired
    private SqlSession sqlSession;

	@Override
	public Page<CarrierPlan> selectCarrierPlanList(CarrierPlan form, Pageable pageable) {
	    // 전체 레코드 수 가져오기
	    int total = sqlSession.selectOne("carrierPlanForm.selectCarrierPlanListCount", form);
	    
	    // 페이징을 위한 파라미터 설정 (Map으로 묶음)
	    Map<String, Object> params = new HashMap<>();
	    params.put("form", form);
	    params.put("offset", pageable.getOffset());
	    params.put("pageSize", pageable.getPageSize());
	    
	    // 페이징된 리스트 가져오기
	    List<CarrierPlan> carrierPlanList = sqlSession.selectList("carrierPlanForm.selectCarrierPlanList", params);
	    
	    // Page 객체로 변환하여 반환
	    return new PageImpl<>(carrierPlanList, pageable, total);
	}
	
	@Override
	public CarrierPlan selectCarrierPlanInfo(Long carrierPlanId) {
		return sqlSession.selectOne("carrierPlanForm.selectCarrierPlanInfo", carrierPlanId);
	}
	
	@Override
	public int modifyCarrierPlan(CarrierPlan form) {
		return sqlSession.update("carrierPlanForm.updateCarrierPlan", form);
	}

	@Override
	public int deleteCarrierPlan(CarrierPlan form) {
		return sqlSession.delete("carrierPlanForm.deleteCarrierPlan", form);
	}

}
