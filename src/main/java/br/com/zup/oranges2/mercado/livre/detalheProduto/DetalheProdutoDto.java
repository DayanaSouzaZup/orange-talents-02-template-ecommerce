package br.com.zup.oranges2.mercado.livre.detalheProduto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import br.com.zup.oranges2.mercado.livre.opiniao.Opinioes;
import br.com.zup.oranges2.mercado.livre.produto.Produto;

public class DetalheProdutoDto {

	private String descricao;
	private String nome;
	private BigDecimal valor;

	private Set<DetalheProdutoCaracteristica> caracteristicas;
	private Set<String> linksImagens;
	private SortedSet<String> perguntas = new TreeSet<>();
	private Set<Map<String, String>> opinioes;

	private double mediaNotas;

	public DetalheProdutoDto(Produto produto) {
		this.descricao = produto.getDescricao();
		this.nome = produto.getNome();
		this.valor = produto.getValor();

		this.caracteristicas = produto.mapeiaCaracteristicas(DetalheProdutoCaracteristica::new);

		this.linksImagens = produto.mapeiaImagens(imagem -> imagem.getLink());

		this.perguntas = produto.mapeiaPerguntas(pergunta -> pergunta.getTitulo());

		Opinioes opinioes = produto.getOpinioes();

		this.opinioes = opinioes.mapeiaOpinioes(opiniao -> {
			return Map.of("titulo", opiniao.getTitulo(), "descricao", opiniao.getDescricao());
		});

		this.mediaNotas = opinioes.media();
	}

	public String getDescricao() {
		return descricao;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Set<DetalheProdutoCaracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public Set<String> getLinksImagens() {
		return linksImagens;
	}

	public SortedSet<String> getPerguntas() {
		return perguntas;
	}

	public Set<Map<String, String>> getOpinioes() {
		return opinioes;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public void setCaracteristicas(Set<DetalheProdutoCaracteristica> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public void setLinksImagens(Set<String> linksImagens) {
		this.linksImagens = linksImagens;
	}

	public void setPerguntas(SortedSet<String> perguntas) {
		this.perguntas = perguntas;
	}

	public void setOpinioes(Set<Map<String, String>> opinioes) {
		this.opinioes = opinioes;
	}

}
