package com.suaninc.newsagency.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.suaninc.newsagency.domain.CarrierTemplate;

public interface TemplateDao {

	public Page<CarrierTemplate> selectTemplateList(CarrierTemplate form, Pageable pageable);
	
	public CarrierTemplate selectTemplateInfo(Long carrierTemplateId);
	
	public int modifyTemplate(CarrierTemplate form);

}
