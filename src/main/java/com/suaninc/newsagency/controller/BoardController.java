package com.suaninc.newsagency.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.suaninc.config.FileStorageProperties;
import com.suaninc.newsagency.domain.ResourcesBoard;
import com.suaninc.newsagency.service.BoardService;

@Controller
//@RequestMapping(value = "/resources/board")
@RequestMapping(value = "/homepage/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
    @Autowired
    private FileStorageProperties fileStorageProperties;
	
    @GetMapping("/list")
    public String list(Model model, ResourcesBoard form, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(required = false) String carrier) throws Exception {
        
        // URL 파라미터로 받은 carrier 값을 form에 설정
        if (carrier != null && !carrier.isEmpty()) {
            form.setCarrier(carrier);
        }
        
        Pageable pageable = PageRequest.of(page, size);
        
        Page<ResourcesBoard> boardList = boardService.getBoardList(form, pageable);
        
        model.addAttribute("boardList", boardList);
        model.addAttribute("selectedCarrier", carrier); // 선택된 carrier를 view로 전달
        
        return "pages/board-list";
    }
    
}
