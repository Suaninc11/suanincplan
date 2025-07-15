package com.suaninc.newsagency.controller;


import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.suaninc.newsagency.domain.CarrierTemplate;
import com.suaninc.newsagency.domain.SalesPolicy;
import com.suaninc.newsagency.domain.TemplateCoordinate;
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
	
	@GetMapping("/salesPolicyList")
	public String salesPolicyList(Model model, SalesPolicy form, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) throws Exception {
		
	    Pageable pageable = PageRequest.of(page, size);
	    
	    Page<SalesPolicy> salesPolicyList = salesPolicyService.getSalesPolicyList(form, pageable);
	    
	    model.addAttribute("salesPolicyList", salesPolicyList);
		
		return "salesPolicyList";
	}
	
	@PostMapping("/uploadExcel")
	public String uploadSalesPolicyExcel(MultipartFile file, RedirectAttributes redirectAttributes) {
	    try {
	        salesPolicyService.uploadSalesPolicyExcel(file);
	        redirectAttributes.addFlashAttribute("message", "업로드 성공");
	    } catch (Exception e) {
	        redirectAttributes.addFlashAttribute("message", "업로드 실패: " + e.getMessage());
	    }
	    return "redirect:/homepage/salesPolicy/salesPolicyList";
	}
	
	@GetMapping("/salesPolicyInfo")
	public String salesPolicyInfo(@RequestParam("id") String id, SalesPolicy form, Model model,
	                           @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) throws Exception {
	    
	    // 1. 템플릿 정보 가져오기
	    SalesPolicy salesPolicyInfo = salesPolicyService.getSalePolicyInfo(id);

	    // 5. Model에 데이터 추가
	    model.addAttribute("salesPolicyInfo", salesPolicyInfo);

	    return "salesPolicyInfo";
	}
    
}
