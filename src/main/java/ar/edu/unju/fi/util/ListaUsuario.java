package ar.edu.unju.fi.util;

import java.util.ArrayList;
import org.springframework.stereotype.Component;
import ar.edu.unju.fi.model.Usuario;
@Component
public class ListaUsuario {
	private ArrayList<Usuario> usuarios;

	public ListaUsuario() {
		this.usuarios = new ArrayList<Usuario>();
	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
}
