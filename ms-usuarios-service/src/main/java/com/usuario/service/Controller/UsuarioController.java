package com.usuario.service.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.service.entity.Usuario;
import com.usuario.service.modelos.Carro;
import com.usuario.service.modelos.Moto;
import com.usuario.service.servicio.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	//LISTA DE USUARIOS
	@GetMapping
	public ResponseEntity <List<Usuario>>ListarUsuarios(){
		List<Usuario> usuarios= usuarioService.getAll();
		if(usuarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(usuarios);
	}
	//ENDOPOINT QUE RETORNA USUARIO POR ID
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtenerUsuario(@PathVariable int id) {
		Usuario usuario = usuarioService.getUsuarioById(id);
		if(usuario==null) {
			return ResponseEntity.notFound().build();
					
		}
		return ResponseEntity.ok(usuario);
	}
	//ENDPOINT QUE CARGA UN USUARIO AL SERVIDOR
	@PostMapping
	public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
		Usuario nuevoUsuario= usuarioService.save(usuario);
		return ResponseEntity.ok(nuevoUsuario);
	}
	//Metodo REST TEMPLATE retorna datos pues es un get
	@GetMapping("/carros/{usuarioId}")
	public ResponseEntity <List<Carro>>ListarCarros(@PathVariable("usuarioId")int id){
		Usuario usuario = usuarioService.getUsuarioById(id);
		if(usuario==null) {
			return ResponseEntity.noContent().build();
		}
		List <Carro> carros = usuarioService.getCarros(id);
		return ResponseEntity.ok(carros);
		
	}
	
	//Metodo REST TEMPLATE retorna datos pues es un get
	@GetMapping("/motos/{usuarioId}")
	public ResponseEntity <List<Moto>>ListarMotos(@PathVariable("usuarioId")int id){
		Usuario usuario = usuarioService.getUsuarioById(id);
		if(usuario==null) {
			return ResponseEntity.noContent().build();
		}
		List <Moto> motos = usuarioService.getMotos(id);
		return ResponseEntity.ok(motos);
	}
	//METODO POST PARA INSERTAR CARRO DESDE USUARIO CON FEINGCLIENT
	@PostMapping("/carro/{usuarioId}")
	public ResponseEntity <Carro> guardarCarro(@PathVariable("usuarioId")int usuarioId,@RequestBody Carro carro){
		Carro nuevoCarro= usuarioService.saveCarro(usuarioId, carro);
		return ResponseEntity.ok(nuevoCarro);
	}
	
	//METODO POST PARA INSERTAR MOTOS DESDE USUARIO CON FEINGCLIENT
	@PostMapping("/moto/{usuarioId}")
	public ResponseEntity <Moto> guardarMoto(@PathVariable("usuarioId")int usuarioId, @RequestBody Moto moto){
		Moto nuevaMoto = usuarioService.saveMoto(usuarioId, moto);
		return ResponseEntity.ok(nuevaMoto);
	}
	
	//METODO PARA RETORNAR TODO DESDE USARIO CON FEINGCLIENT
	@GetMapping("/todo/{usuarioId}")
	public ResponseEntity<Map<String,Object>> listarTodosLosVehiculos(@PathVariable("usuarioId")int usuarioId){
		Map<String,Object> resultado = usuarioService.getUsuarioAndVehiculos(usuarioId);
		return ResponseEntity.ok(resultado);
	}

}
