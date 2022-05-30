package ar.edu.unju.fi.util;

import java.util.ArrayList;
import org.springframework.stereotype.Component;
import ar.edu.unju.fi.model.Candidato;
@Component
public class ListaCandidato {
	private ArrayList<Candidato> candidatos;

	public ListaCandidato() {
		this.candidatos = new ArrayList<Candidato>();
		this.candidatos.add(new Candidato(0,"Ninguno","Otro Genero Musical","no esta el genero musical que me gusta"));
	}

	public ArrayList<Candidato> getCandidatos() {
		return candidatos;
	}

	public void setCandidatos(ArrayList<Candidato> candidatos) {
		this.candidatos = candidatos;
	}


}
