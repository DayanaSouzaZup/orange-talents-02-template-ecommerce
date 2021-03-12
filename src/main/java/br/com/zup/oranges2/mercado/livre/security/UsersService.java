package br.com.zup.oranges2.mercado.livre.security;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.zup.oranges2.mercado.livre.usuario.Usuario;
import br.com.zup.oranges2.mercado.livre.usuario.UsuarioRepository;

@Service
public class UsersService implements UserDetailsService {
	
	private static final String USUARIO_INVALIDO = "Usuário inválido!";

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Usuario> user = usuarioRepository.findByEmail(username);

		if (user.isEmpty()) {
			throw new UsernameNotFoundException(USUARIO_INVALIDO);
		}

		return user.get();
	}

}