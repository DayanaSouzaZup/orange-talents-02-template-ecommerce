package br.com.zup.oranges2.mercado.livre.pergunta;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.oranges2.mercado.livre.produto.Produto;
import br.com.zup.oranges2.mercado.livre.usuario.Usuario;

public class PerguntaDto {

	@NotBlank
	private String titulo;

	public PerguntaDto(@NotBlank String titulo) {
		super();
		this.titulo = titulo;
	}

	public Pergunta toModel(@NotNull @Valid Usuario interessado, @NotNull @Valid Produto produto) {

		return new Pergunta(titulo, produto, interessado);
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
