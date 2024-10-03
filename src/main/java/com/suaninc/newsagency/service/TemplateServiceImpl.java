package com.suaninc.newsagency.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.suaninc.newsagency.dao.TemplateDao;
import com.suaninc.newsagency.domain.CarrierTemplate;
import com.suaninc.newsagency.domain.TemplateCoordinate;

@Service
public class TemplateServiceImpl implements TemplateService {
	
    @Autowired
    private TemplateDao templateDao;

    @Override
    public Page<CarrierTemplate> getTemplateList(CarrierTemplate form, Pageable pageable) {
        return templateDao.selectTemplateList(form, pageable);
    }
    
	@Override
	public List<TemplateCoordinate> getTemplateInfo(String templateCode) {
		return templateDao.selectTemplateInfo(templateCode);
	}
	
	@Override
	public int modifyCoordinate(TemplateCoordinate form) {
		return templateDao.updateCoordinate(form);
	}

}
