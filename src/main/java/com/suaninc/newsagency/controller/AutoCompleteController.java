package com.suaninc.newsagency.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.suaninc.JwtTokenUtil;
import com.suaninc.config.FileStorageProperties;
import com.suaninc.newsagency.domain.ApplyForm;
import com.suaninc.newsagency.domain.CarrierPlan;
import com.suaninc.newsagency.domain.CarrierTemplate;
import com.suaninc.newsagency.domain.TemplateCoordinate;
import com.suaninc.newsagency.service.ApplyFormService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class AutoCompleteController {
	
	@Autowired
	private ApplyFormService applyFormService;
	
    @Autowired
    private FileStorageProperties fileStorageProperties;
    
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @GetMapping("/")
    public String storeTokenInSession(@RequestParam(value = "token", required = false) String token, HttpSession session) {
        if (token != null && jwtTokenUtil.validateToken(token, jwtTokenUtil.extractClientId(token))) {
            session.setAttribute("jwtToken", token); // 세션에 토큰 저장
            System.out.println("Token stored in session: " + token);
        } else {
            System.out.println("No token provided or invalid token. Skipping session storage.");
        }
        // 리다이렉트
        return "redirect:/homepage/templates/joytel";
    }
    
	@GetMapping("/homepage/templates/{templateCode}")
	public String mainPage(@PathVariable String templateCode, ApplyForm form, Model model) throws Exception {
		
		form.setTemplateCode(templateCode);
		if (templateCode.startsWith("uplusuMobile")) {
			templateCode = "uplusuMobile";
		}
		
		List<CarrierPlan> carrierPlanList = applyFormService.getCarrierPlan(templateCode);
		
		model.addAttribute("form", form);
		model.addAttribute("carrierPlanList", carrierPlanList);
		
		return "templates/" + form.getTemplateCode();
	}
	
	@PostMapping("/homepage/inscribeView/autoComplete")
	public void autoComplete(ApplyForm form, HttpServletResponse response) {
	    
	    ClassLoader classLoader = getClass().getClassLoader();
	    
	    try (PDDocument document = new PDDocument()) {
	        List<CarrierTemplate> carrierTemplate = applyFormService.getCarrierTemplate(form);

	        // 체크 이미지 캐싱 (필요시 캐싱하여 재사용)
        	InputStream checkImagePath = classLoader.getResourceAsStream("static/images/checkmark.png");
            BufferedImage checkImage = ImageIO.read(checkImagePath);

	        for (CarrierTemplate template : carrierTemplate) {
	        	
	            if(form.getTemplateCode().equals("joytel")) {
	            	if (form.getWalkInCustomerType().equals("walkInCustomerType1") && template.getTemplateImageOrder() != 1) {
	            		continue;
	            	} else if (form.getWalkInCustomerType().equals("walkInCustomerType2") &&
            	            !(template.getTemplateImageOrder() == 1 || template.getTemplateImageOrder() == 2 || template.getTemplateImageOrder() == 6)) {
	            		continue;
	            	}
	        	}
	            
	        	Path imagePath = Paths.get(fileStorageProperties.getUploadDir(), template.getTemplateCode(), template.getTemplateImageName());
	        	
	        	try (InputStream is = Files.newInputStream(imagePath)) {
	                CarrierTemplate templateImageOrder = new CarrierTemplate();
	                templateImageOrder.setTemplateCode(template.getTemplateCode());
	                templateImageOrder.setTemplateImageOrder(template.getTemplateImageOrder());

	                // 이미지 동기 로드 (이미지가 완전히 로드될 때까지 대기)
	                BufferedImage image = ImageIO.read(is);
	                
	                // Graphics2D 객체 생성
	                Graphics2D g2d = image.createGraphics();
	                
	                try {
	                    List<TemplateCoordinate> templateCoordinateList = applyFormService.getTemplateCoordinateList(templateImageOrder);

	                    for (TemplateCoordinate coordinate : templateCoordinateList) {
	                        try {
	                            // ApplyForm 클래스의 필드가 존재하는지 확인
	                            Field field;
	                            try {
	                                // form 객체의 필드명을 리플렉션으로 가져옴
	                                field = form.getClass().getDeclaredField(coordinate.getTemplateCoordinateName());
	                                field.setAccessible(true);
	                            } catch (NoSuchFieldException e) {
	                                // 필드가 존재하지 않으면 루프를 건너뛰기
	                                System.err.println("필드를 찾을 수 없습니다: " + coordinate.getTemplateCoordinateName());
	                                continue;  // 필드가 없으면 다음 좌표로 이동
	                            }

	                            // 필드가 존재하는 경우 처리
	                            Object value = field.get(form);  // 필드 값을 가져옴
	                            if (value == null) {
	                                System.out.println("필드 값이 null입니다: " + coordinate.getTemplateCoordinateName());
	                                continue;  // null인 경우 해당 좌표에 대한 처리를 건너뜀
	                            }
	                            // 값이 있는 경우, 텍스트 또는 체크박스 처리
	                            String text = value.toString(); 

	                            g2d.setFont(new Font(coordinate.getFontStyle(), Font.PLAIN, coordinate.getFontSize()));
	                            g2d.setColor(Color.BLACK);

	                            int x = coordinate.getCoordinateXAxis();  // X 좌표
	                            int y = coordinate.getCoordinateYAxis();  // Y 좌표

	                            if ("check".equalsIgnoreCase(coordinate.getTemplateInputType())) {
	                                System.out.println("체크 처리: " + coordinate.getTemplateCoordinateName());
	                                if (text.equalsIgnoreCase(coordinate.getTemplateInputOption())) {
	                                    int imgWidth = checkImage.getWidth();
	                                    int imgHeight = checkImage.getHeight();
	                                    g2d.drawImage(checkImage, x - (imgWidth / 2), y - (imgHeight / 2), null);
	                                }
	                            } else {
	                                // 일반 텍스트 그리기
	                                g2d.drawString(text, x, y);
	                            }
	                        } catch (IllegalAccessException e) {
	                            // 필드 접근 실패 시 예외 처리
	                            e.printStackTrace();
	                        }
	                    }
	                } finally {
	                    g2d.dispose(); // 작업이 끝난 후 Graphics2D 리소스 해제
	                }

	                // 수정된 BufferedImage를 다시 바이트 배열로 변환
	                ByteArrayOutputStream baos = new ByteArrayOutputStream();
	                ImageIO.write(image, "jpg", baos);
	                byte[] imageBytes = baos.toByteArray();

	                // PDF에 이미지 추가
	                PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, imageBytes, template.getTemplateImageName());
	                PDPage page = new PDPage(new PDRectangle(pdImage.getWidth(), pdImage.getHeight()));
	                document.addPage(page);

	                try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
	                    contentStream.drawImage(pdImage, 0, 0);
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	                // 파일 읽기 실패 시 예외 처리
	            }
	        }

	        // PDF를 HTTP 응답으로 저장
	        response.setContentType("application/pdf");
	        document.save(response.getOutputStream());
	        response.getOutputStream().flush();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
    
    @GetMapping("/homepage/inscribeView/modify")
    	public String modifyInscribeView(Model model) throws Exception {
    	
    	CarrierTemplate templateImageOrder = new CarrierTemplate();
        templateImageOrder.setTemplateCode("HM001");
        templateImageOrder.setTemplateImageOrder(1);
        
    	List<TemplateCoordinate> templateCoordinateList = applyFormService.getTemplateCoordinateList(templateImageOrder);
        
    	System.out.println("asdasd" + templateCoordinateList.get(1).getTemplateCode());
    	
        model.addAttribute("templateCoordinateList", templateCoordinateList);

        return "modifyInscribeView";
    }

}
