package com.suaninc.newsagency.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.suaninc.newsagency.domain.CarrierTemplate;
import com.suaninc.newsagency.domain.TemplateCoordinate;

public interface TemplateDao {

	Page<CarrierTemplate> selectTemplateList(CarrierTemplate form, Pageable pageable);
	
	List<TemplateCoordinate> selectTemplateInfo(String templateCode);
	
	TemplateCoordinate selectTemplateCoordinateCount(String templateCode);
	
	int updateCoordinate(TemplateCoordinate form);
	
	List<CarrierTemplate> selectTemplateImageList(String templateCode);

	int updateTemplateImageName(CarrierTemplate form);
}
