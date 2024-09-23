package com.suaninc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.suaninc")
public class SuanincplanApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuanincplanApplication.class, args);
	}

}
