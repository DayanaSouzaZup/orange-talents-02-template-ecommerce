package br.com.zup.oranges2.mercado.livre.pergunta;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import br.com.zup.oranges2.mercado.livre.produto.Produto;
import br.com.zup.oranges2.mercado.livre.usuario.Usuario;

@Entity
public class Pergunta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String titulo;

	@NotBlank
	@ManyToOne
	private Produto produto;

	@NotBlank
	@ManyToOne
	private Usuario usuario;

	@Deprecated
	public Pergunta() {
		
	}

	public Pergunta(@NotBlank String titulo, @NotBlank Produto produto, @NotBlank Usuario usuario) {
		super();
		this.titulo = titulo;
		this.produto = produto;
		this.usuario = usuario;
	}


	@Override
	public String toString() {
		return "Pergunta [id=" + id + ", titulo=" + titulo + ", produto=" + produto + ", usuario=" + usuario + "]";
	}


	public Long getId() {
		return id;
	}


	public String getTitulo() {
		return titulo;
	}


	public Produto getProduto() {
		return produto;
	}


	public Usuario getUsuario() {
		return usuario;
	}
	
	

}
