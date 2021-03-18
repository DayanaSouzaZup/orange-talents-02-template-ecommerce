package br.com.zup.oranges2.mercado.livre.compra;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.zup.oranges2.mercado.livre.email.GerenciadorEmail;

public class EventosNovaCompra {
	
	@Autowired
	private Set<EventoCompraSucesso> eventosCompraSucesso;
	@Autowired
	private GerenciadorEmail email;

	public void executa(Compra compra) {

		if (compra.processadaComSucesso()) {
			email.processadaComSucesso(compra);
			eventosCompraSucesso.forEach(evento -> evento.executa(compra));
		} else {
			email.processamentoFalhou(compra);
		}
	}

}
