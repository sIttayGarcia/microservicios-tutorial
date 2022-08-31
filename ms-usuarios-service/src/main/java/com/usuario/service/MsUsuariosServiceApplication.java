package com.usuario.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients //habilita al cliente feing
@SpringBootApplication
public class MsUsuariosServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsUsuariosServiceApplication.class, args);
	}

}
