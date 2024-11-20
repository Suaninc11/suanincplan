package com.suaninc.newsagency.controller;


import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.suaninc.config.FileStorageProperties;
import com.suaninc.newsagency.domain.CarrierTemplate;
import com.suaninc.newsagency.domain.TemplateCoordinate;
import com.suaninc.newsagency.service.TemplateService;

@Controller
public class TemplateController {
	
	@Autowired
	private TemplateService templateService;
	
    @Autowired
    private FileStorageProperties fileStorageProperties;
	
	@GetMapping("/homepage/template/templateList")
	public String templateList(Model model, CarrierTemplate form, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) throws Exception {
		
	    Pageable pageable = PageRequest.of(page, size);
	    
	    Page<CarrierTemplate> templateList = templateService.getTemplateList(form, pageable);
	    
	    model.addAttribute("templateList", templateList);
		
		return "templateList";
	}
	
	@GetMapping("/homepage/template/templateInfo")
	public String templateInfo(@RequestParam("templateCode") String templateCode, TemplateCoordinate form, Model model,
	                           @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) throws Exception {
	    
	    // 1. 템플릿 정보 가져오기
	    List<TemplateCoordinate> templateInfo = templateService.getTemplateInfo(templateCode);
	    List<CarrierTemplate> templateImageList = templateService.getTemplateImageList(templateCode);

	    // 2. 이미지 URL 설정
	    String uploadDir = fileStorageProperties.getUploadDir();
	    templateImageList.forEach(image -> {
	        Path imagePath = Paths.get(uploadDir, templateCode, image.getTemplateImageName());
	        image.setTemplateImageUrl(imagePath.toString());
	    });

	    // 3. 프로퍼티 파일에서 매핑 데이터 읽기
	    Properties properties = new Properties();
	    try (InputStreamReader reader = new InputStreamReader(
	            new ClassPathResource("mapping.properties").getInputStream(), StandardCharsets.UTF_8)) {
	        properties.load(reader);
	    }
	    // 4. Properties 객체를 Map<String, String>으로 변환
	    Map<String, String> coordinateNameMapping = properties.entrySet()
	        .stream()
	        .collect(Collectors.toMap(
	            e -> e.getKey().toString(),
	            e -> e.getValue().toString()
	        ));

	    // 5. Model에 데이터 추가
	    model.addAttribute("templateCode", templateCode);
	    model.addAttribute("templateInfo", templateInfo);
	    model.addAttribute("templateImageList", templateImageList);
	    model.addAttribute("coordinateNameMapping", coordinateNameMapping); // 매핑 데이터 추가

	    return "templateInfo";
	}

	
	@PostMapping("/homepage/template/modifyCoordinate")
	public ResponseEntity<String> modifyTemplate(@RequestBody TemplateCoordinate form) {

	    if (form.getCoordinateXAxis() == null || form.getCoordinateYAxis() == null) {
	        return ResponseEntity.badRequest().body("좌표 값이 누락되었습니다.");
	    }
	    
	    templateService.modifyCoordinate(form);
	    return ResponseEntity.ok("좌표 업데이트 성공");
	}
	
    @PostMapping("/homepage/template/replaceImage")
    public ResponseEntity<Map<String, Object>> replaceImage(@RequestParam("file") MultipartFile file, CarrierTemplate form) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            String filename = file.getOriginalFilename();
            String imgUrl = "/" + form.getTemplateCode() + "/";
            form.setTemplateImageName(filename);
            Path path = Paths.get(fileStorageProperties.getUploadDir(), imgUrl, filename);
            Files.write(path, file.getBytes());
            
            templateService.updateImageName(form);

            response.put("success", true);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
}
