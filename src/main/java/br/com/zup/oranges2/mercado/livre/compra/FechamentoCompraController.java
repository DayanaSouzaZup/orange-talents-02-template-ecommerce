package br.com.zup.oranges2.mercado.livre.compra;


import java.net.URI;
import java.net.URISyntaxException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.oranges2.mercado.livre.email.GerenciadorEmail;
import br.com.zup.oranges2.mercado.livre.produto.Produto;
import br.com.zup.oranges2.mercado.livre.usuario.Usuario;
import br.com.zup.oranges2.mercado.livre.usuario.UsuarioLogado;

@RestController
public class FechamentoCompraController {

	@PersistenceContext
	EntityManager manager;

	@Autowired
	private GerenciadorEmail gerenciadorEmail;

	@PostMapping(value = "/compras")
	@Transactional
	public ResponseEntity<?> criaCompra(@RequestBody @Valid NovaCompraDto compraDto,
			@AuthenticationPrincipal UsuarioLogado usuarioLogado, UriComponentsBuilder uriComponentsBuilder)
			throws URISyntaxException {

		Produto produtoSelecionado = manager.find(Produto.class, compraDto.getIdProduto());

		boolean qtdDisponivel = produtoSelecionado.produtoAbateEstoque(compraDto.getQuantidade());

		if (!qtdDisponivel) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Quantidade solicitada superior a quantidade em estoque");
		}

		Usuario comprador = usuarioLogado.get();

		Compra novaCompra = compraDto.toModel(produtoSelecionado, comprador);

		manager.persist(novaCompra);
		gerenciadorEmail.novaCompra(novaCompra);

		URI urlParaPagamento = new URI(novaCompra.urlRedirecionamento(uriComponentsBuilder));

		return ResponseEntity.status(302).location(urlParaPagamento).build();
	}

}
