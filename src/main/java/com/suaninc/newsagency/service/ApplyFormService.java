package com.suaninc.newsagency.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.suaninc.newsagency.model.TemplateCoordinate;
import com.suaninc.newsagency.model.CarrierTemplate;
import com.suaninc.newsagency.model.CommonCode;
import com.suaninc.newsagency.repository.ApplyFormRepository;


@Service
public class ApplyFormService {

	private ApplyFormRepository applyFormRepository;

    public ApplyFormService(ApplyFormRepository applyFormRepository) {
        this.applyFormRepository = applyFormRepository;
    }

    public List<CommonCode> getCarrierList() {
        return applyFormRepository.selectCarrierList();
    }
    
    public List<CarrierTemplate> findByCode() {
        return applyFormRepository.findByCode(Integer carrierTemplateId));
    }
    
    public List<TemplateCoordinate> getTemplateCoordinateList() {
        return applyFormRepository.selectTemplateCoordinateList();
    }
	
}
