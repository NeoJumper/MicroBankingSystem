package com.kcc.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.retry.annotation.EnableRetry;
// 왜 안돼..
@SpringBootApplication
@EnableRetry
public class MicroBankingSystemApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MicroBankingSystemApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(MicroBankingSystemApplication.class, args);
	}

}
