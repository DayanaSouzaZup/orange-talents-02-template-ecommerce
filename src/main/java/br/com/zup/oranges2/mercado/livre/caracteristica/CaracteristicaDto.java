package br.com.zup.oranges2.mercado.livre.caracteristica;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.oranges2.mercado.livre.produto.Produto;

public class CaracteristicaDto {

	@NotBlank
	private String nome;

	@NotBlank
	private String descricao;

	public CaracteristicaDto(@NotBlank String nome, @NotBlank String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "CaracteristicaDto [nome=" + nome + ", descricao=" + descricao + "]";
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public Caracteristica toModel(@NotNull @Valid Produto produto) {
		
		return new Caracteristica(nome, descricao, produto);
	}

}
