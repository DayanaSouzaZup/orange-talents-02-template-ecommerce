package br.com.zup.oranges2.mercado.livre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.oranges2.mercado.livre.dto.CategoriaDto;
import br.com.zup.oranges2.mercado.livre.dto.CategoriaResponse;
import br.com.zup.oranges2.mercado.livre.entity.Categoria;

@RestController
public class CategoriaController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping("/categorias")
	@Transactional
	public CategoriaResponse cadastraCategoria(@RequestBody @Valid CategoriaDto categoriaDto) {
		
		Categoria categoria = categoriaDto.toModel(manager);
		manager.persist(categoria);
		return new CategoriaResponse(categoria);
		
		
		
		
	}

}
