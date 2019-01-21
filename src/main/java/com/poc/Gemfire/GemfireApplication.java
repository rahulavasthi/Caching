package com.poc.Gemfire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.gemfire.cache.config.EnableGemfireCaching;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

@EnableGemfireRepositories
@EnableGemfireCaching
@SpringBootApplication
public class GemfireApplication {

	public static void main(String[] args) {
		SpringApplication.run(GemfireApplication.class, args);
	}
}
