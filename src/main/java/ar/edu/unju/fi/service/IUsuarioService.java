package ar.edu.unju.fi.service;

import ar.edu.unju.fi.model.Usuario;
import ar.edu.unju.fi.util.ListaUsuario;

public interface IUsuarioService {
	public Usuario getUsuario();
	public boolean guardarUsuario(Usuario usuario);
	public ListaUsuario getListaUsuario();
	public boolean emitirVotoUsuario(String email);
	public Usuario buscarUsuario(String email);

}
