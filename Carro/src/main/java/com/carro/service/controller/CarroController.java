package com.carro.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carro.service.entity.Carro;
import com.carro.service.servicio.CarroService;

@RestController
@RequestMapping("/carro")
public class CarroController {
	
	@Autowired
	private CarroService carroService;
	
	@GetMapping
	public ResponseEntity <List<Carro>> listarCarros(){
		List<Carro> carros = carroService.getAll();
		if(carros.isEmpty()) {
			return ResponseEntity.noContent().build();		
			}
		return ResponseEntity.ok(carros);
		
	}
	@GetMapping("/{id}")
	public ResponseEntity <Carro> obtenerCarro(@PathVariable("id") int id){
		Carro carros = carroService.getCarroById(id);
		if(carros==null) {
			return ResponseEntity.noContent().build();		
			}
		return ResponseEntity.ok(carros);
	}

	
	 @PostMapping
	 public ResponseEntity <Carro> guardarCarro(@RequestBody Carro carro){
		 Carro NuevoCarro= carroService.save(carro);
		 return ResponseEntity.ok(NuevoCarro);
	 }
	 
	 @GetMapping("/usuario/{usuarioId}")
	 public ResponseEntity <List<Carro>> ListarCarrosPorUsuariosId(@PathVariable ("usuarioId") int id){
		 List <Carro> carros = carroService.byUsuarioId(id);
		 if(carros.isEmpty()) {
			 return ResponseEntity.noContent().build();
			 
		 }
		 return ResponseEntity.ok(carros);
		 }

}
