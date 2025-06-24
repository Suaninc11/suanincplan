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
    public String mainPage(SalesPolicy form, Model model) {

        List<String> contractPeriods = salesPolicyService.getDistinctContractPeriods();
        List<String> activationTypes = salesPolicyService.getDistinctActivationTypes();
        List<String> productNames = salesPolicyService.getDistinctProductNames();
        List<String> planNames = salesPolicyService.getDistinctPlanNames();

        model.addAttribute("form", form);
        model.addAttribute("contractPeriods", contractPeriods);
        model.addAttribute("activationTypes", activationTypes);
        model.addAttribute("productNames", productNames);
        model.addAttribute("planNames", planNames);

        return "templates/ktmMobileDevice";
    }
    
    @PostMapping("/getOnlineUrl")
    @ResponseBody
    public Map<String, String> getOnlineUrl(@RequestBody SalesPolicy form) {
        SalesPolicy urlInfo = salesPolicyService.getUrlPathByConditions(form);
        Map<String, String> map = new HashMap<>();

        if (urlInfo != null) {
            map.put("pcUrl", urlInfo.getUrlPath());
            map.put("mobileUrl", urlInfo.getMobileUrlPath());
        } else {
            map.put("pcUrl", "");
            map.put("mobileUrl", "");
        }
        return map;
    }
    
}
