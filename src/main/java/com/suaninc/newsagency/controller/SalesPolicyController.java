package com.suaninc.newsagency.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.suaninc.newsagency.domain.SalesPolicy;
import com.suaninc.newsagency.service.SalesPolicyService;

@Controller
@RequestMapping("/homepage/salesPolicy")
public class SalesPolicyController {
	
	@Autowired
	private SalesPolicyService salesPolicyService;
	
	@GetMapping("/ktmMobileDevice")
	public String ktmMobileDevice(SalesPolicy form, Model model) {
		
	    model.addAttribute("form", form);
	    model.addAttribute("contractPeriods", salesPolicyService.getDistinctContractPeriods());
	    
	    return "templates/ktmMobileDevice";
	}
	
	@ResponseBody
	@PostMapping("/getActivationTypes")
	public List<String> getActivationTypes(@RequestBody Map<String, String> request) {
	    return salesPolicyService.getActivationTypesByContractPeriod(request.get("contractPeriod"));
	}
	
	@ResponseBody
	@PostMapping("/getProductNames")
	public List<String> getProductNames(@RequestBody SalesPolicy form) {
	    return salesPolicyService.getProductNamesByConditions(form);
	}

	@ResponseBody
	@PostMapping("/getPlanNames")
	public List<String> getPlanNames(@RequestBody SalesPolicy form) {
	    return salesPolicyService.getPlanNamesByConditions(form);
	}
	
	@PostMapping("/getPlanDetail")
	@ResponseBody
	public SalesPolicy getPlanDetail(@RequestBody SalesPolicy form) {
	    return salesPolicyService.getPlanDetail(form);
	}

	@ResponseBody
	@PostMapping("/getOnlineUrl")
	public Map<String, String> getOnlineUrl(@RequestBody SalesPolicy form) {
	    SalesPolicy policy = salesPolicyService.getUrlPathByConditions(form);
	    Map<String, String> result = new HashMap<>();
	    if (policy != null) {
	        result.put("pcUrl", policy.getUrlPath());
	        result.put("mobileUrl", policy.getMobileUrlPath());
	    }
	    return result;
	}
    
}
