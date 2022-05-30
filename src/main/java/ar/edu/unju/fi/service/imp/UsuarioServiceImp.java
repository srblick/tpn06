package ar.edu.unju.fi.service.imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.util.ListaUsuario;
import ar.edu.unju.fi.model.Usuario;
import ar.edu.unju.fi.service.IUsuarioService;

@Service("UsuarioServiceImpList")
public class UsuarioServiceImp implements IUsuarioService{
	@Autowired
	private ListaUsuario listaUsuario;
	
	@Override
	public Usuario getUsuario() {
		// retorna un objeto de la clase Usuario
		return new Usuario();
	}

	@Override
	public boolean guardarUsuario(Usuario usuario) {
		// guarda un objeto usuario en la lista de usuarios
		// devuelve true si se guardo correctamente sino devuelve false
		return listaUsuario.getUsuarios().add(usuario);
	}

	@Override
	public ListaUsuario getListaUsuario() {
		// Devuelve la lista de usuarios
		return listaUsuario;
	}

	@Override
	public boolean emitirVotoUsuario(String email) {
		// busca el usuario por su email y actualiza cantidadVotos
		boolean voto = false;
		for(Usuario user:  listaUsuario.getUsuarios()) {
			if(user.getEmail().equals(email)) {
				voto = user.emitirVoto();
			}
		}
		return voto;
	}
	
	@Override
	public Usuario buscarUsuario(String email) {
		Optional<Usuario> usuario = listaUsuario.getUsuarios().stream().filter(c -> c.getEmail().equals(email)).findFirst();
		return usuario.get();
	}

}
