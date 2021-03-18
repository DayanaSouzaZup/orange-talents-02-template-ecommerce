package br.com.zup.oranges2.mercado.livre.compra;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.PathVariable;

public class RetornoPagSeguroDto {
	
	@Override
	public String toString() {
		return "RetornoPagSeguroDto [idTransacao=" + idTransacao + ", status=" + status + "]";
	}

	@NotBlank
	private String idTransacao;
	
	@NotNull
	private StatusSeguroPagSeguro status;

	public RetornoPagSeguroDto(@PathVariable("id") Long idCompra, @NotNull StatusSeguroPagSeguro status) {
		super();
		this.idTransacao = idTransacao;
		this.status = status;
	}

	public Transacao toTransacao(Compra compra) {

		return new Transacao(status.normaliza(), idTransacao, compra);
	}
	
	

}
