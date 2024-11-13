package com.kcc.banking;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.retry.annotation.EnableRetry;

import java.util.TimeZone;

@SpringBootApplication
@EnableRetry
public class MicroBankingSystemApplication extends SpringBootServletInitializer {


	public static void main(String[] args) {
		//TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
		SpringApplication.run(MicroBankingSystemApplication.class, args);
	}

}
