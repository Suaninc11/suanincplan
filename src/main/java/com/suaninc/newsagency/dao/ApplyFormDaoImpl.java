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

import com.suaninc.newsagency.domain.ApplyForm;
import com.suaninc.newsagency.domain.CarrierPlan;
import com.suaninc.newsagency.domain.CarrierTemplate;
import com.suaninc.newsagency.domain.CommonCode;
import com.suaninc.newsagency.domain.TemplateCoordinate;

@Repository
public class ApplyFormDaoImpl implements ApplyFormDao {

    @Autowired
    private SqlSession sqlSession;

	@Override
	public List<CommonCode> selectCarrierList() {
		return sqlSession.selectList("applyForm.selectCarrierList");
	}
	
	@Override
	public List<CarrierTemplate> selectCarrierTemplate(ApplyForm form) {
		return sqlSession.selectList("applyForm.selectCarrierTemplate", form);
	}
	
	@Override
	public List<CarrierPlan> selectCarrierPlan(ApplyForm form) {
		return sqlSession.selectList("applyForm.selectCarrierPlan", form);
	}

	@Override
	public List<TemplateCoordinate> selectTemplateCoordinateList(CarrierTemplate templateImageOrder) {
		return sqlSession.selectList("applyForm.selectTemplateCoordinateList", templateImageOrder);
	}

}