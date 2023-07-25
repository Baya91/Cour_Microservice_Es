package com.spring.usuario.service.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.spring.usuario.service.entidades.Usuario;
import com.spring.usuario.service.feinClients.CarroFeignClient;
import com.spring.usuario.service.feinClients.MotoFeignClient;
import com.spring.usuario.service.modelos.Carro;
import com.spring.usuario.service.modelos.Moto;
import com.spring.usuario.service.repositorio.UsuarioRepository;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

	
	
	 /****************************code Microservice avec RestTemplate/****************************/
    @Autowired
	private RestTemplate restTemplate;
    
    @Override    //  getCarros avec RestTemplate
	public List<Carro> getCarros(Long usuarioId) {
		List<Carro> carros = restTemplate.getForObject("http://carro-service/carro/usuario/"+usuarioId, List.class);
		return carros;
	}

    @Override //  getMotos avec RestTemplate
	public List<Moto> getMotos(Long usuarioId) {
		List<Moto> motos = restTemplate.getForObject("http://moto-service/moto/usuario/" + usuarioId, List.class);
		return motos;
	}
	
	
	
	
    /****************************code Microservice avec Feign  Carro/****************************/
    @Autowired
    private CarroFeignClient carroFeignClient;

    @Override
    public Carro saveCarro(Long usuarioId, Carro carro) {
        carro.setUsuarioId(usuarioId);
        return this.carroFeignClient.save(carro);
    }
    
    /*
    @Override    //  getCarros avec Fiegn
    public List<Carro> getCarros(Long usuarioId) {
        return this.carroFeignClient.getCarrosByUsuario(usuarioId);
    }
/*
    /****************************code Microservice avec Feign  Moto/****************************/
    @Autowired
    private MotoFeignClient motoFeignClient;
    @Override
    public Moto saveMoto(Long usuarioId, Moto moto) {
        moto.setUsuarioId(usuarioId);
        return this.motoFeignClient.save(moto);
    }

  /* @Override //  getMotos avec Fiegn
    public List<Moto> getMotos(Long usuarioId) {
        return this.motoFeignClient.getMotosByUsuario(usuarioId);
    }*/

    /**************************** Get Todos ****************************/
    @Override
    public Map<String, Object> getTodos(Long UsuarioId) {
        Map<String, Object> resulta = new HashMap<>();

        Usuario usuario = this.usuarioRepository.findById(UsuarioId).get();
        if (usuario == null) {
            resulta.put("Mensaje", " Este Usuario no existe");
        } else {
            resulta.put("usuario", usuario);
        }

        List<Carro> listaCarros = this.carroFeignClient.getCarrosByUsuario(UsuarioId);
        if (listaCarros.isEmpty()) {
            resulta.put("Mensaje", " Este Usuario no tiene Carros");
        } else {
            resulta.put("carros", listaCarros);
        }

        List<Moto> listaMotos = this.motoFeignClient.getMotosByUsuario(UsuarioId);
        if (listaMotos.isEmpty()) {
            resulta.put("Mensaje", " Este Usuario no tiene Motos");
        } else {
            resulta.put("motos", listaMotos);
        }

        return resulta;
    }

    /****************************code Usuario/****************************/
    @Autowired
    private UsuarioRepository usuarioRepository;

    

    
    @Override
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    
    @Override
    public Usuario getUsuarioById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario save(Usuario usuario) {
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return nuevoUsuario;
    }

}
