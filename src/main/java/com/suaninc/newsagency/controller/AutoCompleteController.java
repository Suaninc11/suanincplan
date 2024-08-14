package com.suaninc.newsagency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/newsagency/autoComplete")
public class AutoCompleteController {
	
	@GetMapping("/")
    public String mainPage() {
        return "redirect:/newsagency/autoComplete/inscribe";
    }

	@GetMapping("/inscribe")
	public String autoCompleteForm(Model model) {
		
		return "/newsagency_inscribe";
	}
	  
}
