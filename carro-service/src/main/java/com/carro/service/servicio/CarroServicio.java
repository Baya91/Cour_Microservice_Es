package com.carro.service.servicio;

import java.util.List;

import com.carro.service.entidades.Carro;


public interface CarroServicio {

    public List<Carro> getAll();
    
    
    public Carro getCarroById(Long id) ;
    
    
    public Carro save(Carro carro);
    
    
    public List<Carro> byUsuarioId(Long usuarioId);
    
}
