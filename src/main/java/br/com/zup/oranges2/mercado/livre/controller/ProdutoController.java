package br.com.zup.oranges2.mercado.livre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.oranges2.mercado.livre.dto.ProdutoDto;
import br.com.zup.oranges2.mercado.livre.entity.Produto;
import br.com.zup.oranges2.mercado.livre.entity.Usuario;
import br.com.zup.oranges2.mercado.livre.repository.UsuarioRepository;
import br.com.zup.oranges2.mercado.livre.validation.ProibeNomeIgualDeCaracteristicaValidator;

@RestController
public class ProdutoController {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@InitBinder
	public void init(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new ProibeNomeIgualDeCaracteristicaValidator());
	}

	@PostMapping("/produtos")
	@Transactional

	public ResponseEntity<Produto> cadastraProduto(@RequestBody @Valid ProdutoDto produtoDto) {
		Usuario usuario = Usuario.findAuthenticatedUser(usuarioRepository);
		Produto novoProduto = produtoDto.toModel(manager, usuario);
		manager.persist(novoProduto);
		return ResponseEntity.ok(novoProduto);

	}
}
