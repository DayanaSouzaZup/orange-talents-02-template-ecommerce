package br.com.zup.oranges2.mercado.livre.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;

	@ManyToOne
	private Categoria categoriaMae;

	@Deprecated
	public Categoria() {

	}

	public Categoria(@NotBlank String nome, Categoria categoriaMae) {
		this.nome = nome;
		this.categoriaMae = categoriaMae;
	}

	public Categoria getCategoriaMae() {
		return categoriaMae;
	}

	public String getNome() {
		return nome;
	}

}
