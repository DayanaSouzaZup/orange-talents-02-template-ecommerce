package br.com.zup.oranges2.mercado.livre.dto;

import br.com.zup.oranges2.mercado.livre.entity.Categoria;

public class CategoriaResponse {

	private String nome;
	private Categoria categoriaMae;

	public CategoriaResponse(Categoria categoria) {
		this.nome = categoria.getNome();
		this.categoriaMae = categoria.getCategoriaMae();
	}

	public String getNome() {
		return nome;
	}

	public Categoria getCategoriaMae() {
		return categoriaMae;
	}

}
