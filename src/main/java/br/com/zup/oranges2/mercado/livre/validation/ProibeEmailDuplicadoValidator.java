package br.com.zup.oranges2.mercado.livre.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zup.oranges2.mercado.livre.usuario.Usuario;
import br.com.zup.oranges2.mercado.livre.usuario.UsuarioDto;
import br.com.zup.oranges2.mercado.livre.usuario.UsuarioRepository;

@Component
public class ProibeEmailDuplicadoValidator implements Validator {

	@Autowired
	private UsuarioRepository usuarioRepository;

//	public ProibeEmailDuplicadoValidator(UsuarioRepository usuarioRepository) {
//		this.usuarioRepository = usuarioRepository;
//	}

	@Override
	public boolean supports(Class<?> clazz) {

		return UsuarioDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		if (errors.hasErrors()) {
			return;
		}

		UsuarioDto usuarioDto = (UsuarioDto) target;
		
		Optional<Usuario> possivelUsuario = usuarioRepository.findByEmail(usuarioDto.getEmail());

		if (possivelUsuario.isPresent()) {
			errors.rejectValue("email", null, "Email já cadastrado no sistema");
		}

	}

}
