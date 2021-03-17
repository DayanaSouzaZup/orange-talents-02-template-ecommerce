package br.com.zup.oranges2.mercado.livre.detalheProduto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.oranges2.mercado.livre.produto.Produto;

@RestController
public class DetalheProdutoController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@GetMapping(value = "/produtos{id}")
	public DetalheProdutoDto buscaDetalhe(@PathVariable ("id") Long id) {
		Produto produtoEscolhido = manager.find(Produto.class, id);
		
		return new DetalheProdutoDto(produtoEscolhido);

	}

}
