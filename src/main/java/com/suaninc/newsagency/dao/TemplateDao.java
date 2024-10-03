package com.suaninc.newsagency.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.suaninc.newsagency.domain.CarrierTemplate;
import com.suaninc.newsagency.domain.TemplateCoordinate;

public interface TemplateDao {

	public Page<CarrierTemplate> selectTemplateList(CarrierTemplate form, Pageable pageable);
	
	public List<TemplateCoordinate> selectTemplateInfo(String templateCode);
	
	public int updateCoordinate(TemplateCoordinate form);

}
