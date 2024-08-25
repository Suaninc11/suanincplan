package com.suaninc.newsagency.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.suaninc.newsagency.dto.ApplyformDto;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class AutoCompleteController {
	
	@GetMapping("/inscribeView")
	public String mainPage(Model model) {
		
		return "inscribeView";
	}
	
	@PostMapping("/autoComplete")
	 public static void addTextToImage(@ModelAttribute ApplyformDto data, HttpServletResponse response) {
		
       try {
    	   
    	   System.out.println("#############" + data.getPhoneNumber());
    	   
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
	  
	  
}
