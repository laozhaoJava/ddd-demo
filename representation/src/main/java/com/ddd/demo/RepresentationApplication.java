package com.ddd.demo;

import org.apache.servicecomb.springboot2.starter.EnableServiceComb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"com.ddd.demo",
		"com.shinemo.demo"
})
@EnableServiceComb
public class RepresentationApplication {

	public static void main(String[] args) {
		SpringApplication.run(RepresentationApplication.class, args);
	}

}
