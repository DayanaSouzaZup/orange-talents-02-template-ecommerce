package br.com.zup.oranges2.mercado.livre.compra;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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

}
