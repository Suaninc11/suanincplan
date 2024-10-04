package com.suaninc.newsagency.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.suaninc.newsagency.domain.CarrierTemplate;
import com.suaninc.newsagency.domain.TemplateCoordinate;
import com.suaninc.newsagency.service.TemplateService;

@Controller
public class TemplateController {
	
	@Autowired
	private TemplateService templateService;
	
	@GetMapping("/homepage/template/templateList")
	public String templateList(Model model, CarrierTemplate form, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) throws Exception {
		
	    Pageable pageable = PageRequest.of(page, size);
	    
	    Page<CarrierTemplate> templateList = templateService.getTemplateList(form, pageable);
	    
	    model.addAttribute("templateList", templateList);
		
		return "templateList";
	}
	
	@GetMapping("/homepage/template/templateInfo")
	public String templateInfo(@RequestParam("templateCode") String templateCode,TemplateCoordinate form, Model model,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) throws Exception {
		
		List<TemplateCoordinate> templateInfo = templateService.getTemplateInfo(templateCode);
	    
		model.addAttribute("templateInfo", templateInfo);
	    
		return "templateInfo";
	}
	
	@PostMapping("/homepage/template/modifyCoordinate")
	public ResponseEntity<String> modifyTemplate(@RequestBody TemplateCoordinate form) {

	    // 유효성 검사: 필수 필드가 비어 있으면 400 오류 반환
	    if (form.getCoordinateXAxis() == null || form.getCoordinateYAxis() == null) {
	        return ResponseEntity.badRequest().body("좌표 값이 누락되었습니다.");
	    }
	    
	    templateService.modifyCoordinate(form);
	    return ResponseEntity.ok("좌표 업데이트 성공");
	}

}
