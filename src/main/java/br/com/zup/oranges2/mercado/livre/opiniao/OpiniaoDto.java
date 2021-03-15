package br.com.zup.oranges2.mercado.livre.opiniao;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zup.oranges2.mercado.livre.produto.Produto;
import br.com.zup.oranges2.mercado.livre.usuario.Usuario;

public class OpiniaoDto {

	@NotBlank
	@Min(1)
	@Max(5)
	private String nota;

	@NotBlank
	private String titulo;

	@NotBlank
	@Size(max = 500)
	private String descricao;

	public OpiniaoDto(@NotNull @Min(1) @Max(5) String nota, @NotNull String titulo,
			@NotBlank @Size(max = 500) String descricao) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "OpiniaoDto [nota=" + nota + ", titulo=" + titulo + ", descricao=" + descricao + "]";
	}

	public Opiniao toModel(@NotNull @Valid Produto produto, @NotNull @Valid Usuario consumidor) {
		return new Opiniao(nota, titulo, descricao, produto, consumidor);
	}

}
