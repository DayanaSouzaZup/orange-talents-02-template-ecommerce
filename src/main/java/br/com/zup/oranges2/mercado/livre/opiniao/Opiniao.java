package br.com.zup.oranges2.mercado.livre.opiniao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zup.oranges2.mercado.livre.produto.Produto;
import br.com.zup.oranges2.mercado.livre.usuario.Usuario;

@Entity
public class Opiniao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Min(1)
	@Max(5)
	private String nota;

	@NotBlank
	private String titulo;

	@NotBlank
	@Size(max = 500)
	private String descricao;

	@ManyToOne
	@NotNull
	@Valid
	private Produto produto;

	@ManyToOne
	@NotNull
	@Valid
	private Usuario consumidor;

	@Deprecated
	public Opiniao() {

	}

	public Opiniao(@NotBlank @Min(1) @Max(5) String nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao, @NotNull @Valid Produto produto,
			@NotNull @Valid Usuario consumidor) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.produto = produto;
		this.consumidor = consumidor;
	}

	@Override
	public String toString() {
		return "Opiniao [id=" + id + ", nota=" + nota + ", titulo=" + titulo + ", descricao=" + descricao + ", produto="
				+ produto + ", consumidor=" + consumidor + "]";
	}

	public String getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Usuario getUsuario() {
		return consumidor;
	}

}
