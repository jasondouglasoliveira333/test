package com.jdo.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableWebMvc
public class ConsultaMedicaApp {
	public static void main(String... args) {
		SpringApplication.run(ConsultaMedicaApp.class);
	}

}