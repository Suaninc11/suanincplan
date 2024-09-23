package com.suaninc.newsagency.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.suaninc.newsagency.domain.CarrierPlan;
import com.suaninc.newsagency.domain.CarrierTemplate;
import com.suaninc.newsagency.domain.CommonCode;
import com.suaninc.newsagency.service.TemplateService;

@Controller
public class TemplateController {
	
	@Autowired
	private TemplateService templateService;
	
	@GetMapping("/homepage/template/templateList")
	public String templateList(Model model, CommonCode form, 
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) throws Exception {
		
	    Pageable pageable = PageRequest.of(page, size);
	    
	    Page<CommonCode> templateList = templateService.getTemplateList(form, pageable);
	    
	    model.addAttribute("templateList", templateList);
		
		return "templateList";
	}
	
	@GetMapping("/homepage/template/templateInfo")
	public String templateInfo(@RequestParam("id") Long carrierTemplateId, Model model, CarrierPlan form, 
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) throws Exception {
		
		CarrierTemplate templateInfo = templateService.getTemplateInfo(carrierTemplateId);
	    
		model.addAttribute("templateInfo", templateInfo);
	    
		return "templateInfo";
	}

}
