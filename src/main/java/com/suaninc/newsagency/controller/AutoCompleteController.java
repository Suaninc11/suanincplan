package com.suaninc.newsagency.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
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
import org.springframework.web.bind.annotation.PostMapping;

import com.suaninc.newsagency.domain.ApplyForm;
import com.suaninc.newsagency.domain.CarrierPlan;
import com.suaninc.newsagency.domain.CarrierTemplate;
import com.suaninc.newsagency.domain.CommonCode;
import com.suaninc.newsagency.domain.TemplateCoordinate;
import com.suaninc.newsagency.service.ApplyFormService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class AutoCompleteController {
	
	@Autowired
	private ApplyFormService applyFormService;
	
	@GetMapping("/homepage/inscribeView")
	public String mainPage(Model model) throws Exception {
		
		List<CommonCode> carrierList = applyFormService.getCarrierList();
		
		model.addAttribute("carrierList", carrierList);
		
		return "inscribeView";
	}
	
	@PostMapping("/homepage/templates/mobileCarrier")
	public String templates(Model model, ApplyForm form) {
		
		List<CarrierPlan> carrierPlanList = applyFormService.getCarrierPlanList(form);

		String returnPage = "templates/helloMobile";
		if (form.getCodeDescription() != null && !form.getCodeDescription().isEmpty()) {
		    returnPage = "templates/" + form.getCodeDescription();
		}

		model.addAttribute("carrierPlanList", carrierPlanList);
		
		return returnPage;
	}
	
	@PostMapping("/homepage/inscribeView/autoComplete")
	public void autoComplete(ApplyForm form, HttpServletResponse response) {
	    try (PDDocument document = new PDDocument()) {
	        List<CarrierTemplate> carrierTemplate = applyFormService.getCarrierTemplate(form);

	        for (int i = 0; i < carrierTemplate.size(); i++) {
	            InputStream is = AutoCompleteController.class.getResourceAsStream("/images/" + carrierTemplate.get(i).getTemplateImagePath() + "/" + carrierTemplate.get(i).getTemplateImageName());

	            CarrierTemplate templateImageOrder = new CarrierTemplate();
	            templateImageOrder.setTemplateCode(carrierTemplate.get(i).getTemplateCode());
	            templateImageOrder.setTemplateImageOrder(carrierTemplate.get(i).getTemplateImageOrder());

	            BufferedImage image = ImageIO.read(is);
	            is.close();

	            List<TemplateCoordinate> templateCoordinateList = applyFormService.getTemplateCoordinateList(templateImageOrder);

	            for (TemplateCoordinate coordinate : templateCoordinateList) {
	                try {
	                    // ApplyForm 클래스의 필드를 리플렉션을 통해 동적으로 가져오기
	                    Field field = form.getClass().getDeclaredField(coordinate.getTemplateCoordinateName());
	                    field.setAccessible(true);
	                    
	                    Object value = field.get(form); // 필드 값을 가져옴

	                    String text;
	                    if (value instanceof String) {
	                        text = (String) value;
	                    } else if (value instanceof Integer) {
	                        text = String.valueOf(value);
	                    } else {
	                        text = value != null ? value.toString() : ""; // 다른 타입인 경우 문자열로 변환
	                    }

	                    if (text != null) {
	                        // 텍스트를 그리기 위해 Graphics2D 객체 사용
	                        Graphics2D g2d = image.createGraphics();
	                        g2d.setFont(new Font("Malgun Gothic", Font.PLAIN, 11)); // Arial Malgun Gothic
	                        g2d.setColor(Color.BLACK);

	                        // 텍스트 그리기
	                        int x = coordinate.getCoordinateXAxis(); // X 좌표
	                        int y = coordinate.getCoordinateYAxis(); // Y 좌표
	                        
	                        // 텍스트가 "check"인 경우 체크 표시 그리기
	                        if (coordinate.getTemplateInputType().equals("check")) {
	                            if (text.equalsIgnoreCase(coordinate.getTemplateInputOption())) {
	                                // 체크 표시 이미지를 불러옵니다.
	                                BufferedImage checkImage = ImageIO.read(AutoCompleteController.class.getResourceAsStream("/images/checkmark.png"));
	                                int imgWidth = checkImage.getWidth();
	                                int imgHeight = checkImage.getHeight();
	                                
	                                // 체크 표시 이미지를 그립니다.
	                                g2d.drawImage(checkImage, x - (imgWidth / 2), y - (imgHeight / 2), null);
	                            }
	                        } else {
	                            g2d.drawString(text, x, y); // 일반 텍스트 그리기
	                        }
	                        
	                        g2d.dispose();
	                    }
	                } catch (NoSuchFieldException e) {
	                    System.err.println("필드를 찾을 수 없습니다: " + coordinate.getTemplateCoordinateName());
	                } catch (IllegalAccessException e) {
	                    // 필드 접근 실패 시 예외 처리
	                    e.printStackTrace();
	                }
	            }

	            // 수정된 BufferedImage를 다시 바이트 배열로 변환
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            ImageIO.write(image, "jpg", baos);
	            byte[] imageBytes = baos.toByteArray();

	            PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, imageBytes, carrierTemplate.get(i).getTemplateImageName());
	            PDPage page = new PDPage(new PDRectangle(pdImage.getWidth(), pdImage.getHeight()));
	            document.addPage(page);

	            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
	                contentStream.drawImage(pdImage, 0, 0);
	            }
	        }

	        response.setContentType("application/pdf");
	        document.save(response.getOutputStream());
	        response.getOutputStream().flush();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	@GetMapping("/homepage/inscribeView/carrierPlan")
	public String carrierPlan(Model model, ApplyForm form) throws Exception {
		
		List<CommonCode> carrierList = applyFormService.getCarrierList();
		
		model.addAttribute("carrierList", carrierList);
		
		return "carrierPlan";
	}

}
