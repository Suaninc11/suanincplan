package com.suaninc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.suaninc.JwtTokenUtil;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/scss/**").addResourceLocations("classpath:/static/scss/");
        registry.addResourceHandler("/vendor/**").addResourceLocations("classpath:/static/vendor/");
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/static/fonts/");
        registry.addResourceHandler("/external-images/**").addResourceLocations("file:///D:/dev/images/", "file:/opt/app/images/");
    }
    
    @Bean
    JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil();
    }
}
