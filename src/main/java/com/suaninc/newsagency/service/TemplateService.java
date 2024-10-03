package com.suaninc.newsagency.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.suaninc.newsagency.domain.CarrierTemplate;
import com.suaninc.newsagency.domain.TemplateCoordinate;

public interface TemplateService {

	// 템플릿 목록
    public Page<CarrierTemplate> getTemplateList(CarrierTemplate form, Pageable pageable);
	
	// 템플릿 상세
	public List<TemplateCoordinate> getTemplateInfo(String templateCode);
	
	// 템플릿 좌표 수정
	public int modifyCoordinate(TemplateCoordinate form);
	
}
