package com.usuario.service.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.usuario.service.entity.Usuario;
import com.usuario.service.feingclients.CarroFeingClient;
import com.usuario.service.feingclients.MotoFeingClient;
import com.usuario.service.modelos.Carro;
import com.usuario.service.modelos.Moto;
import com.usuario.service.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;//API Rest
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CarroFeingClient carroFeingClient;
	
	@Autowired
	private MotoFeingClient motoFeingClient;
	//Metodo rest template
	public List<Carro> getCarros (int usuarioId){
	List<Carro> carros = restTemplate.getForObject("http://localhost:8002/carro/usuario/"+ usuarioId,List.class);
	return carros;
	}
	//Metodo rest template
	public List <Moto> getMotos (int usuarioId){
		List <Moto> motos= restTemplate.getForObject("http://localhost:8005/moto/usuario/" + usuarioId, List.class);
		return motos;
	}
	//Metodo FEINGCLIENT
	public Carro saveCarro(int usuarioId, Carro carro) {
		carro.setUsuarioId(usuarioId);
		Carro nuevoCarro = carroFeingClient.save(carro);
		return nuevoCarro;
			
	}
	//Metodo FEINGCLIENT
	public Moto saveMoto (int usuarioId, Moto moto) {
		moto.setUsuarioId(usuarioId);
		Moto nuevaMoto = motoFeingClient.save(moto);
		return nuevaMoto;
	}
	
	//Metodo que retorna vehiculos y motos de cada usuario FEINGCLIENT
	public Map<String, Object> getUsuarioAndVehiculos(int usuarioId) {
		Map<String, Object> resultado = new HashMap<>();
		Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

		if (usuario == null) {
			resultado.put("Mensaje", "El usario no existe");
			return resultado;
		}
		resultado.put("Usuario", usuario);
		List<Carro> carros = carroFeingClient.getCarros(usuarioId);
		if (carros.isEmpty()) {
			resultado.put("Carros", "El usuario no tiene carros");
		} else {
			resultado.put("Carros", carros);
		}
		List<Moto> motos = motoFeingClient.getMotos(usuarioId);
		if (motos.isEmpty()) {
			resultado.put("Motos", "El usuario no tiene motos");
		} else {
			resultado.put("Motos", motos);
		}
		return resultado;
	}

	public List<Usuario> getAll() {
		return usuarioRepository.findAll();

	}

	public Usuario getUsuarioById(int id) {
		return usuarioRepository.findById(id).orElse(null);

	}

	public Usuario save(Usuario usuario) {
		Usuario nuevoUsuario = usuarioRepository.save(usuario);
		return nuevoUsuario;
	}

}
