package br.com.zup.oranges2.mercado.livre.pergunta;

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
public class PerguntaController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping(value = "/produtos/{id}/perguntas")
	@Transactional
	public String cadastraPergunta(@RequestBody @Valid PerguntaDto respostaDto, @PathVariable ("id") Long id) {
		Produto produto = manager.find(Produto.class, id);
		Usuario interessado = Usuario.findAuthenticatedUser(usuarioRepository);
		Pergunta novaPergunta = respostaDto.toModel(interessado, produto);
		
		manager.persist(novaPergunta);
		return novaPergunta.toString();
	}

}
