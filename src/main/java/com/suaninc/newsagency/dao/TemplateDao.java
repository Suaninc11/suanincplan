package com.suaninc.newsagency.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.suaninc.newsagency.domain.CarrierTemplate;
import com.suaninc.newsagency.domain.CommonCode;

public interface TemplateDao {

	public Page<CommonCode> selectTemplateList(CommonCode form, Pageable pageable);
	
	public CarrierTemplate selectTemplateInfo(Long carrierTemplateId);
	
	public int modifyTemplate(CarrierTemplate form);

}
