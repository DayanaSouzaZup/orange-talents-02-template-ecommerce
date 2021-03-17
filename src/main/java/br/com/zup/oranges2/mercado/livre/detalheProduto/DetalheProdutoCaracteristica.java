package br.com.zup.oranges2.mercado.livre.detalheProduto;

import br.com.zup.oranges2.mercado.livre.caracteristica.Caracteristica;

public class DetalheProdutoCaracteristica {

	private String nome;
	private String descrica;

	public DetalheProdutoCaracteristica(Caracteristica caracteristica) {
		this.nome = caracteristica.getNome();
		this.descrica = caracteristica.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public String getDescrica() {
		return descrica;
	}

}
