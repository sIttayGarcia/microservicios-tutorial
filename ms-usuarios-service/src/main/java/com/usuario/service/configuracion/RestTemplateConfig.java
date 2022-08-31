package com.usuario.service.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//clase para conectar microservicios
//Guardar bean se guardan en el contenedor de Spring
import org.springframework.web.client.RestTemplate;
@Configuration
public class RestTemplateConfig {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
