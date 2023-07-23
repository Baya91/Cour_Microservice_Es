package com.moto.service.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moto.service.entidades.Moto;
import com.moto.service.repositorio.MotoRepository;

@Service
public class MotoServiceImpl implements MotoService {

	@Autowired
	private MotoRepository motoRepository;

	@Override
	public List<Moto> getAll() {
		return motoRepository.findAll();
	}
	@Override
	public Moto getMotoById(Long id) {
		return motoRepository.findById(id).orElse(null);
	}
	@Override
	public Moto save(Moto moto) {
		Moto nuevaMoto = motoRepository.save(moto);
		return nuevaMoto;
	}
	@Override
	public List<Moto> byUsuarioId(Long usuarioId) {
		return motoRepository.findByUsuarioId(usuarioId);
	}
}
