package com.suaninc.newsagency.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suaninc.newsagency.domain.CarrierPlan;
import com.suaninc.newsagency.service.CarrierPlanService;

@Controller
public class CarrierPlanController {
	
	@Autowired
	private CarrierPlanService carrierPlanService;
	
	@GetMapping("/homepage/carrierPlan/carrierPlanList")
	public String carrierPlanList(Model model, CarrierPlan form, 
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) throws Exception {
		
	    Pageable pageable = PageRequest.of(page, size);
	    
	    Page<CarrierPlan> carrierPlanList = carrierPlanService.getCarrierPlanList(form, pageable);
	    
	    model.addAttribute("carrierPlanList", carrierPlanList);
	    model.addAttribute("carrierPlan", form);
		
		return "pages/carrier-plan-list";
	}
	
	@GetMapping("/homepage/carrierPlan/carrierPlanInfo")
	public String carrierPlanInfo(@RequestParam("id") Long carrierPlanId, Model model, CarrierPlan form, 
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) throws Exception {
		
		CarrierPlan carrierPlanInfo = carrierPlanService.getCarrierPlanInfo(carrierPlanId);
	    
		model.addAttribute("carrierPlanInfo", carrierPlanInfo);
	    
		return "pages/carrier-plan-info";
	}
	
	@PostMapping("/homepage/carrierPlan/carrierPlanModify")
	@ResponseBody
	public Map<String, Object> carrierPlanModify(CarrierPlan form) {
		Map<String, Object> result = new HashMap<>();
	    
	    try {
	        carrierPlanService.modifyCarrierPlan(form);
	        result.put("success", true);
	        result.put("message", "수정이 완료되었습니다.");
	        result.put("redirectUrl", "/homepage/carrierPlan/carrierPlanInfo?id=" + form.getCarrierPlanId());
	    } catch (Exception e) {
	        result.put("success", false);
	        result.put("message", "수정에 실패했습니다. 오류: " + e.getMessage());
	    }

	    return result;
	}

	
	@PostMapping("/homepage/carrierPlan/deleteCarrierPlan")
	@ResponseBody
	public Map<String, Object> deleteCarrierPlan(CarrierPlan form) {
	    Map<String, Object> response = new HashMap<>();

	    try {
	        carrierPlanService.deleteCarrierPlan(form);
	        response.put("success", true);
	        response.put("message", "삭제가 완료되었습니다.");
	        response.put("redirectUrl", "/homepage/carrierPlan/carrierPlanList");
	    } catch (Exception e) {
	        response.put("success", false);
	        response.put("message", "삭제에 실패했습니다. 오류: " + e.getMessage());
	    }

	    return response;
	}
	
	@GetMapping("/homepage/carrierPlan/carrierPlanForm")
	public String carrierPlanForm() {
		
	    return "pages/carrier-plan-form";
	}

	@PostMapping("/homepage/carrierPlan/createCarrierPlan")
	@ResponseBody
	public Map<String, Object> createCarrierPlan(CarrierPlan form) {
	    Map<String, Object> response = new HashMap<>();

	    try {
	        carrierPlanService.createCarrierPlan(form);
	        response.put("success", true);
	        response.put("message", "추가가 완료되었습니다.");
	        response.put("redirectUrl", "/homepage/carrierPlan/carrierPlanList");
	    } catch (Exception e) {
	        response.put("success", false);
	        response.put("message", "추가에 실패했습니다. 오류: " + e.getMessage());
	    }

	    return response;
	}
}
