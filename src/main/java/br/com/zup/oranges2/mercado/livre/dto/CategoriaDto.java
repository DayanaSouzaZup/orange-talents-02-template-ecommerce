package br.com.zup.oranges2.mercado.livre.dto;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

import org.springframework.lang.Nullable;

import br.com.zup.oranges2.mercado.livre.entity.Categoria;
import br.com.zup.oranges2.mercado.livre.validation.ExistsId;
import br.com.zup.oranges2.mercado.livre.validation.UniqueValue;

public class CategoriaDto {

	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	private String nome;

	@Nullable
	@ExistsId(domainClass = Categoria.class, fieldName = "id", message = "A Categoria mãe ainda não existe")
	private Long idCategoriaMae;

	public CategoriaDto(@NotBlank String nome, Long idCategoriaMae) {
		super();
		this.nome = nome;
		this.idCategoriaMae = idCategoriaMae;
	}

	@Deprecated
	public CategoriaDto() {

	}

	public Categoria toModel(EntityManager manager) {
		
		Categoria categoria = null;

		if (idCategoriaMae != null) {
			Categoria categoriaMae = manager.find(Categoria.class, idCategoriaMae);
			return categoria = new Categoria(nome, categoriaMae);
		}
		
		return categoria = new Categoria(nome, null);
	}

	public String getNome() {
		return nome;
	}

	public Long getIdCategoriaMae() {
		return idCategoriaMae;
	}

}
