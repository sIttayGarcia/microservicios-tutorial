package com.moto.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.moto.service.entity.Moto;
import com.moto.service.servicio.MotoService;

@RestController
@RequestMapping ("/moto")
public class MotoController {
	
	@Autowired
	private MotoService motoService;
	
	@GetMapping
	public ResponseEntity <List<Moto>> listarMotos (){
		List <Moto>motos = motoService.getAll();
		if(motos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(motos);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <Moto> obtenerMoto(@PathVariable("id") int id){
		Moto motos = motoService.getMotoById(id);
		if(motos==null) {
			return ResponseEntity.noContent().build();		
			}
		return ResponseEntity.ok(motos);
	}
	@PostMapping
	 public ResponseEntity <Moto> guardarMoto(@RequestBody Moto moto){
		 Moto NuevaMoto= motoService.save(moto);
		 return ResponseEntity.ok(NuevaMoto);
	 }
	 @GetMapping("/usuario/{usuarioId}")
	 public ResponseEntity <List<Moto>> ListarMotosPorUsuariosId(@PathVariable ("usuarioId") int id){
		 List <Moto> motos = motoService.byUsuarioId(id);
		 if(motos.isEmpty()) {
			 return ResponseEntity.noContent().build();
			 
		 }
		 return ResponseEntity.ok(motos);
		 }
	

}
