package com.kcc.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class MicroBankingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroBankingSystemApplication.class, args);
	}

}
