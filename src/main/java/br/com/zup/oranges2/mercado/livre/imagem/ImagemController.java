package br.com.zup.oranges2.mercado.livre.imagem;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.zup.oranges2.mercado.livre.produto.Produto;
import br.com.zup.oranges2.mercado.livre.validation.ProibeNomeIgualDeCaracteristicaValidator;

public class ImagemController {
	
	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private Uploader uploader;

	@InitBinder
	public void init(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new ProibeNomeIgualDeCaracteristicaValidator());
	}
	
	@PostMapping(value = "/produtos/{id}/imagens")
	@Transactional
	public String adicionaImagens(@PathVariable("id") Long id, @Valid NovasImagensDto imagensDto) {

		Set<String> links = uploader.envia(imagensDto.getImagens());
		Produto produto = manager.find(Produto.class, id);
		produto.associaImagens(links);
		
		manager.merge(produto);
		return produto.toString();
	}


}
