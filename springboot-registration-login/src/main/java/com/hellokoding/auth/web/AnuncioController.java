package com.hellokoding.auth.web;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hellokoding.auth.model.Anuncio;
import com.hellokoding.auth.repository.AnuncioRepository;

@Controller
public class AnuncioController {
	@Autowired
	private AnuncioRepository repAnuncios;
	@PostConstruct
	public void init() { 
		repAnuncios.save(new Anuncio("Pepe", "Hola..", "XXXX")); 
		repAnuncios.save(new Anuncio("Juan", "Adios...", "XXXX")); 
	}
	
	@GetMapping("/contacto") 
	public String contacto(Model model) {
	model.addAttribute("anuncios", repAnuncios.findAll());
	return "tablon";
	}
	
	//@GetMapping("/tablon")
	//public String tablon
}
