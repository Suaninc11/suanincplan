package com.suaninc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.suaninc")
@MapperScan("com.suaninc.newsagency.mapper")
public class SuanincplanApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuanincplanApplication.class, args);
	}

}
