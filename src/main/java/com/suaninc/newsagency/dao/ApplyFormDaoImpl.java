package com.suaninc.newsagency.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.suaninc.newsagency.domain.ApplyForm;
import com.suaninc.newsagency.domain.CarrierPlan;
import com.suaninc.newsagency.domain.CarrierTemplate;
import com.suaninc.newsagency.domain.TemplateCoordinate;

@Repository
public class ApplyFormDaoImpl implements ApplyFormDao {

    @Autowired
    private SqlSession sqlSession;

	@Override
	public List<CarrierTemplate> selectCarrierTemplate(ApplyForm form) {
		return sqlSession.selectList("applyForm.selectCarrierTemplate", form);
	}
	
	@Override
	public List<CarrierPlan> selectCarrierPlan(String templateCode) {
		return sqlSession.selectList("applyForm.selectCarrierPlan", templateCode);
	}

	@Override
	public List<TemplateCoordinate> selectTemplateCoordinateList(CarrierTemplate templateImageOrder) {
		return sqlSession.selectList("applyForm.selectTemplateCoordinateList", templateImageOrder);
	}
	
	@Override
	public List<TemplateCoordinate> selectTemplateCoordinates(String templateCode) {
		return sqlSession.selectList("applyForm.selectTemplateCoordinates", templateCode);
	}

}
