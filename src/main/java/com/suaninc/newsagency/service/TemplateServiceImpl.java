package com.suaninc.newsagency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.suaninc.newsagency.dao.TemplateDao;
import com.suaninc.newsagency.domain.CarrierTemplate;

@Service
public class TemplateServiceImpl implements TemplateService {
	
    @Autowired
    private TemplateDao templateDao;

    @Override
    public Page<CarrierTemplate> getTemplateList(CarrierTemplate form, Pageable pageable) {
        return templateDao.selectTemplateList(form, pageable);
    }
    
	@Override
	public CarrierTemplate getTemplateInfo(Long carrierTemplateId) {
		return templateDao.selectTemplateInfo(carrierTemplateId);
	}
	
	@Override
	public int modifyTemplate(CarrierTemplate form) {
		return templateDao.modifyTemplate(form);
	}

}
