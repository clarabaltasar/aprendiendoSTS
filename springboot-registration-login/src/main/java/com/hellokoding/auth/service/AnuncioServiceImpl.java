package com.hellokoding.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hellokoding.auth.model.Anuncio;
import com.hellokoding.auth.repository.AnuncioRepository;

public class AnuncioServiceImpl implements AnuncioService {
	@Autowired
	private AnuncioRepository anunciorepo;
	
	public void save(Anuncio anuncio) {
		anunciorepo.save(anuncio);
	}

	@Override
	public List<Anuncio> mostrar() {
		return anunciorepo.findAll();
	}
	
}
