package br.com.zup.oranges2.mercado.livre.compra;

public interface RetornoGatewayPagamento {
	
	Transacao toTransacao(Compra compra);

}
