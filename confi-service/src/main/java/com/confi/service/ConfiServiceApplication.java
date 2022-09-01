package com.confi.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfiServiceApplication.class, args);
	}

}
