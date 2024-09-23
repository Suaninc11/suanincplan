package com.suaninc.newsagency.dao;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
=======
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 6d971a4cc491035c9e57842d3fa3e8643b8f5651
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
<<<<<<< HEAD
	public List<CarrierTemplate> selectCarrierTemplate(ApplyForm form) {
		return sqlSession.selectList("applyForm.selectCarrierTemplate", form);
	}
	
	@Override
	public List<CarrierPlan> selectCarrierPlan(ApplyForm form) {
		return sqlSession.selectList("applyForm.selectCarrierPlan", form);
	}
=======
	public List<CarrierPlan> selectCarrierPlanList(ApplyForm form) {
		return sqlSession.selectList("applyForm.selectCarrierPlanList", form);
	}

	@Override
	public List<CarrierTemplate> selectCarrierTemplate(ApplyForm form) {
		return sqlSession.selectList("applyForm.selectCarrierTemplate", form);
	}
>>>>>>> 6d971a4cc491035c9e57842d3fa3e8643b8f5651

	@Override
	public List<TemplateCoordinate> selectTemplateCoordinateList(CarrierTemplate templateImageOrder) {
		return sqlSession.selectList("applyForm.selectTemplateCoordinateList", templateImageOrder);
	}
<<<<<<< HEAD

=======
>>>>>>> 6d971a4cc491035c9e57842d3fa3e8643b8f5651
}
