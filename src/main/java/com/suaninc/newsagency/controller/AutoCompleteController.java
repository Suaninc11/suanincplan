package com.suaninc.newsagency.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.suaninc.newsagency.model.TemplateCoordinate;
import com.suaninc.newsagency.model.CommonCode;
import com.suaninc.newsagency.service.ApplyFormService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class AutoCompleteController {
	
	@Autowired
	private ApplyFormService applyFormService;
	
    public AutoCompleteController(ApplyFormService applyFormService) {
        this.applyFormService = applyFormService;
    }
    
	@GetMapping("/homepage/inscribeView")
	public String mainPage(Model model) throws Exception {
		
		List<CommonCode> carrierList = applyFormService.getCarrierList();
		
		model.addAttribute("carrierList", carrierList);
		
		return "inscribeView";
	}
	
	@GetMapping("/homepage/templates/helloMobile")
	public String templates(Model model) {
		
		return "templates/helloMobile";
	}
	
	@PostMapping("/homepage/inscribeView/autoComplete")
	 public static void addTextToImage(@ModelAttribute TemplateCoordinate data, HttpServletResponse response) {
		
       try {
           InputStream is = AutoCompleteController.class.getResourceAsStream("/images/helloMobile/helloMobile_01.jpg");
           if (is == null) {
               System.out.println("Resource not found");
               return;
           }
           BufferedImage image = ImageIO.read(is);
           
           Graphics2D g2d = image.createGraphics();
           
           g2d.setFont(new Font("Malgun Gothic", Font.PLAIN, 11));
           g2d.setColor(Color.BLACK);
           
           // 텍스트 위치 설정
           String text = "박윤상";
           int x = 230;
           int y = 387;
           
           // 텍스트 그리기
           g2d.drawString(text, x, y);  // 텍스트 위치 조정 필요
           g2d.dispose();

           response.setContentType("image/jpeg");
           ByteArrayOutputStream baos = new ByteArrayOutputStream();
           ImageIO.write(image, "jpg", baos);
           byte[] imageData = baos.toByteArray();

           response.getOutputStream().write(imageData);
           response.getOutputStream().flush();
           response.getOutputStream().close();
           
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
	
	@PostMapping("/homepage/inscribeView/autoComplete")
	public void autoComplete(@ModelAttribute TemplateCoordinate data, HttpServletResponse response) {
	    try (PDDocument document = new PDDocument()) {
	    	
	    	List<CarrierTemplate> templateCoordinateList = applyFormService.getTemplateCoordinateList();

			List<TemplateCoordinate> templateCoordinateList = applyFormService.getTemplateCoordinateList();
	    	
	    	// 이미지 파일 목록
	        String[] images = {"helloMobile_01.jpg", "helloMobile_02.jpg", "helloMobile_03.jpg"};
	
	        for (String imageFile : images) {
	        	InputStream is = AutoCompleteController.class.getResourceAsStream("/images/helloMobile/" + imageFile);
	
	            BufferedImage image = ImageIO.read(is);
   
	            Graphics2D g2d = image.createGraphics();
           
            	g2d.setFont(new Font("Malgun Gothic", Font.PLAIN, 11));
        		g2d.setColor(Color.BLACK);
   
    			// 텍스트 위치 설정
        		String text = "박윤상";
        		int x = 230;
        		int y = 387;
   
        		// 텍스트 그리기
        		g2d.drawString(text, x, y);  // 텍스트 위치 조정 필요
        		g2d.dispose();
	            
        		PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, is.readAllBytes(), imageFile);
        		PDPage page = new PDPage(new PDRectangle(pdImage.getWidth(), pdImage.getHeight()));
        		document.addPage(page);

	            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
	                contentStream.drawImage(pdImage, 0, 0);
	            }
            is.close();
	        }
	
	        response.setContentType("application/pdf");
	        document.save(response.getOutputStream());
	        response.getOutputStream().flush();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
