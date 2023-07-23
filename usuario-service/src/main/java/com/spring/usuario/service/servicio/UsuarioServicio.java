package com.spring.usuario.service.servicio;

import java.util.List;
import java.util.Map;

import com.spring.usuario.service.entidades.Usuario;
import com.spring.usuario.service.modelos.Carro;
import com.spring.usuario.service.modelos.Moto;

public interface UsuarioServicio {

    /****************************code Usuario/****************************/
    public List<Usuario> getAll();

    public Usuario getUsuarioById(Long id);

    public Usuario save(Usuario usuario);

    /****************************code Microservice Carro/****************************/
    public Carro saveCarro(Long usuarioId, Carro carro);

    /****************************code Microservice Moto/****************************/
    public Moto saveMoto(Long usuarioId, Moto moto);

    public List<Moto> getMotos(Long usuarioId);
    
    /****************************get  Todos/****************************/
    public Map<String, Object> getTodos(Long UsuarioId);
}
