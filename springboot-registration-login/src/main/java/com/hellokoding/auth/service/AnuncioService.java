package com.hellokoding.auth.service;

import java.util.List;

import com.hellokoding.auth.model.Anuncio;

public interface AnuncioService {
	public void save(Anuncio anuncio);
	
	public List<Anuncio> mostrar();

}
