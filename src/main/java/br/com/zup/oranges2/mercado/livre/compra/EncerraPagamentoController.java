package br.com.zup.oranges2.mercado.livre.compra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EncerraPagamentoController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private EventosNovaCompra eventosNovaCompra;
	
	@PostMapping(value = "/retorno-pagseguro/{id}")
	@Transactional
	public String finalizaCompra(@PathVariable("id") Long idCompra, @Valid RetornoPayPalDto retornoDto ) {
		
		Compra compra = manager.find(Compra.class, idCompra);
		compra.adicionaTransacao(retornoDto);
		
		manager.merge(compra);
		return retornoDto.toString();
	}
	
	@PostMapping(value = "/retorno-paypal/{id}")
	@Transactional
	public String processamentoPaypal(@PathVariable("id") Long idCompra, @Valid RetornoPayPalDto retornoDto) {
		return processa(idCompra, retornoDto);
	}

	private String processa(Long idCompra, RetornoGatewayPagamento retornoGatewayPagamento) {
		Compra compra = manager.find(Compra.class, idCompra);
		compra.adicionaTransacao(retornoGatewayPagamento);
		manager.merge(compra);

		eventosNovaCompra.executa(compra);
	
		return compra.toString();
	}

}
