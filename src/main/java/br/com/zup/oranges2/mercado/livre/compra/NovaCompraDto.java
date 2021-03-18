package br.com.zup.oranges2.mercado.livre.compra;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.oranges2.mercado.livre.produto.Produto;
import br.com.zup.oranges2.mercado.livre.usuario.Usuario;

public class NovaCompraDto {

	@Positive
	private int quantidade;

	@NotNull
	private Long idProduto;

	@NotNull
	private GatewayPagamento gateway;

	public NovaCompraDto(@Positive int quantidade, @NotNull Long idProduto, GatewayPagamento gateway) {
		super();
		this.quantidade = quantidade;
		this.idProduto = idProduto;
		this.gateway = gateway;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public GatewayPagamento getGateway() {
		return gateway;
	}

	public Compra toModel(Produto produtoSelecionado, Usuario comprador) {
		return new Compra(produtoSelecionado, quantidade, comprador, this.gateway);
	}
}
