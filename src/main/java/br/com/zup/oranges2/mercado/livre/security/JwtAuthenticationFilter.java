package br.com.zup.oranges2.mercado.livre.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.zup.oranges2.mercado.livre.entity.Usuario;
import br.com.zup.oranges2.mercado.livre.repository.UsuarioRepository;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private TokenService tokenService;

	private UsuarioRepository userRepository;

	public JwtAuthenticationFilter() {

	}

	public JwtAuthenticationFilter(TokenService tokenService, UsuarioRepository userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = this.getToken(request);
		
		boolean isValid = tokenService.isTokenValid(token);

		if (isValid) {
			autenticateUser(token);
		}

		filterChain.doFilter(request, response);
	}

	private void autenticateUser(String token) {

		String username = this.tokenService.getUsername(token);

		Usuario usuario = userRepository.findByEmail(username).get();
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null,
				usuario.getAuthorities());

		SecurityContextHolder.getContext().setAuthentication(authentication);

	}

	private String getToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");

		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7, token.length());
	}
}
