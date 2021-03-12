package br.com.zup.oranges2.mercado.livre.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.zup.oranges2.mercado.livre.entity.Usuario;

public class UsuarioDto {

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Length(min = 6)
	private String senha;

	public UsuarioDto(@NotBlank @Email String email, @NotBlank @Length(min = 6) String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	@Deprecated
	public UsuarioDto() {

	}

	@Override
	public String toString() {
		return "UsuarioDto [email=" + email + ", senha=" + senha + "]";
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public Usuario toUsuario(PasswordEncoder passwordEncoder) {

		return new Usuario(email, passwordEncoder.encode(senha));
	}

}
