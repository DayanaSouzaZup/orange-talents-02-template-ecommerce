package br.com.zup.oranges2.mercado.livre.compra;

public enum StatusSeguroPagSeguro {
	
	SUCESSO, ERRO;

	public StatusTransacao normaliza() {
		if(this.equals(SUCESSO)) {
			return StatusTransacao.sucesso;
		}
		return StatusTransacao.erro;
	}
}
