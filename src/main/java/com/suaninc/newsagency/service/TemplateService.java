package com.suaninc.newsagency.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.suaninc.newsagency.domain.CarrierTemplate;
import com.suaninc.newsagency.domain.CommonCode;

public interface TemplateService {

	// 템플릿 목록
	public Page<CommonCode> getTemplateList(CommonCode form, Pageable pageable);
	
	// 템플릿 상세
	public CarrierTemplate getTemplateInfo(Long carrierTemplateId);
	
	// 템플릿 수정
	public int modifyTemplate(CarrierTemplate form);
	
}
