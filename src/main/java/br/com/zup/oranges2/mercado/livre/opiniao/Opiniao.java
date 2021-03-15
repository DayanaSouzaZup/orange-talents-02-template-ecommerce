//package br.com.zup.oranges2.mercado.livre.opiniao;
//
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//
//import br.com.zup.oranges2.mercado.livre.produto.Produto;
//import br.com.zup.oranges2.mercado.livre.usuario.Usuario;
//
//public class Opiniao {
//
//	private Integer nota;
//
//	@NotNull
//	private String titulo;
//
//	@NotBlank
//	@Size(max = 500)
//	private String descricao;
//
//	@NotBlank
//	private Usuario usuario;
//
//	@NotBlank
//	private Produto perguntaDirecionada;
//
//	@Deprecated
//	public Opiniao() {
//
//	}
//
//	public Opiniao(Integer nota, @NotNull String titulo, @NotBlank @Size(max = 500) String descricao,
//			@NotBlank Usuario usuario, @NotBlank Produto perguntaDirecionada) {
//		super();
//		this.nota = nota;
//		this.titulo = titulo;
//		this.descricao = descricao;
//		this.usuario = usuario;
//		this.perguntaDirecionada = perguntaDirecionada;
//	}
//
//	@Override
//	public String toString() {
//		return "Opiniao [nota=" + nota + ", titulo=" + titulo + ", descricao=" + descricao + ", usuario=" + usuario
//				+ ", perguntaDirecionada=" + perguntaDirecionada + "]";
//	}
//
//	public Integer getNota() {
//		return nota;
//	}
//
//	public String getTitulo() {
//		return titulo;
//	}
//
//	public String getDescricao() {
//		return descricao;
//	}
//
//	public Usuario getUsuario() {
//		return usuario;
//	}
//
//	public Produto getPerguntaDirecionada() {
//		return perguntaDirecionada;
//	}
//
//}
