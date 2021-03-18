package br.com.zup.oranges2.mercado.livre.produto;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.com.zup.oranges2.mercado.livre.caracteristica.Caracteristica;
import br.com.zup.oranges2.mercado.livre.caracteristica.CaracteristicaDto;
import br.com.zup.oranges2.mercado.livre.categoria.Categoria;
import br.com.zup.oranges2.mercado.livre.imagem.ImagemProduto;
import br.com.zup.oranges2.mercado.livre.opiniao.Opiniao;
import br.com.zup.oranges2.mercado.livre.opiniao.Opinioes;
import br.com.zup.oranges2.mercado.livre.pergunta.Pergunta;
import br.com.zup.oranges2.mercado.livre.usuario.Usuario;
import io.jsonwebtoken.lang.Assert;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;

	@NotNull
	@Positive
	private BigDecimal valor;

	@Positive
	private int quantidade;

	@NotBlank
	@Length(max = 1000)
	private String descricao;

	@NotNull
	@Valid
	@ManyToOne
	private Categoria categoria;

	@NotNull
	@Valid
	@ManyToOne
	private Usuario dono;

	@JsonManagedReference
	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<Caracteristica> caracteristicas = new HashSet<>();

	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<ImagemProduto> imagens = new HashSet<>();

	@OneToMany(mappedBy = "produto")
	@OrderBy("titulo asc")
	private Set<Pergunta> pergunta = new HashSet<>();

	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<Opiniao> opinioes = new HashSet<>();

	public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor, @Positive int quantidade,
			@NotBlank @Size(max = 1000) String descricao, @NotNull @Valid Categoria categoria,
			@NotNull @Valid Usuario dono, @Size(min = 3) @Valid Collection<CaracteristicaDto> caracteristicas) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.dono = dono;
		this.caracteristicas.addAll(caracteristicas.stream().map(caracteristica -> caracteristica.toModel(this))
				.collect(Collectors.toSet()));
	}

	@Deprecated
	public Produto() {

	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", valor=" + valor + ", quantidade=" + quantidade
				+ ", descricao=" + descricao + ", categoria=" + categoria + ", dono=" + dono + ", caracteristicas="
				+ caracteristicas + ", imagens=" + imagens + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Usuario getDono() {
		return dono;
	}

	public Set<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public void associaImagens(Set<String> links) {
		Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(this, link))
				.collect(Collectors.toSet());
		this.imagens.addAll(imagens);
	}

	public boolean pertenceAoUsuario(Usuario possivelDono) {

		return this.dono.equals(possivelDono);
	}

	public <T> Set<T> mapeiaCaracteristicas(Function<Caracteristica, T> funcaoMapeadora) {
		return this.caracteristicas.stream().map(funcaoMapeadora).collect(Collectors.toSet());
	}

	public <T> Set<T> mapeiaImagens(Function<ImagemProduto, T> funcaoMapeadora) {
		return this.imagens.stream().map(funcaoMapeadora).collect(Collectors.toSet());
	}

	public <T extends Comparable<T>> SortedSet<T> mapeiaPerguntas(Function<Pergunta, T> funcaoMapeadora) {
		return this.pergunta.stream().map(funcaoMapeadora).collect(Collectors.toCollection(TreeSet::new));
	}

	public Opinioes getOpinioes() {
		return new Opinioes(this.opinioes);
	}

	public boolean produtoAbateEstoque(@Positive int quantidade) {
		Assert.isTrue(quantidade > 0, "A quantidade deve ser maior que zero");
		
		if(quantidade <= this.quantidade) {
			this.quantidade-=quantidade;
			return true;
		}
		
		return false;
		
	}

}
