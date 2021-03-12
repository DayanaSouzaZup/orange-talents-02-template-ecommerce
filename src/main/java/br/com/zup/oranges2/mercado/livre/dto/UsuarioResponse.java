package br.com.zup.oranges2.mercado.livre.dto;

import java.time.LocalDateTime;

import br.com.zup.oranges2.mercado.livre.entity.Usuario;

public class UsuarioResponse {
	
	private String email;
	private LocalDateTime instante;

	public UsuarioResponse(Usuario usuario) {
		this.email = usuario.getEmail();
		this.instante = usuario.getInstanteCriacao();
	}

	public String getEmail() {
		return email;
	}

	public LocalDateTime getInstante() {
		return instante;
	}

}
