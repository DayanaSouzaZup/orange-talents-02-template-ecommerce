package br.com.zup.oranges2.mercado.livre.usuario;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/usuarios")
	@Transactional

	public UsuarioResponse cadastrarUsuario(@RequestBody @Valid UsuarioDto usuarioDto) {

		Usuario usuario = usuarioDto.toUsuario(passwordEncoder);
		manager.persist(usuario);
		return new UsuarioResponse(usuario);
	}
}
