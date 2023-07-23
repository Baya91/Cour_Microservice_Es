package com.moto.service.servicios;

import java.util.List;


import com.moto.service.entidades.Moto;


public interface MotoService {


	public List<Moto> getAll();
	public Moto getMotoById(Long id) ;
	public Moto save(Moto moto);
	public List<Moto> byUsuarioId(Long usuarioId) ;
	
}
