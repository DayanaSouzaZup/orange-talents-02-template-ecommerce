package br.com.zup.oranges2.mercado.livre.opiniao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.oranges2.mercado.livre.produto.Produto;
import br.com.zup.oranges2.mercado.livre.usuario.Usuario;
import br.com.zup.oranges2.mercado.livre.usuario.UsuarioRepository;

@RestController
public class OpiniaoController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	
	@PostMapping(value = "/produtos/{id}/opinioes")
	@Transactional
	public String cadastraOpiniao(@RequestBody @Valid OpiniaoDto opiniaoDto, @PathVariable Long id) {
		
		Produto produto = manager.find(Produto.class, id);
		Usuario consumidor = Usuario.findAuthenticatedUser(usuarioRepository);
		Opiniao opiniaoDada = opiniaoDto.toModel(produto, consumidor);
		
		manager.persist(opiniaoDada);
		return opiniaoDada.toString();
	}
	

}
