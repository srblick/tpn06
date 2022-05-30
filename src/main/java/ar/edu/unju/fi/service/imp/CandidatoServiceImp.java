package ar.edu.unju.fi.service.imp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.model.Candidato;
import ar.edu.unju.fi.service.ICandidatoService;
import ar.edu.unju.fi.util.ListaCandidato;
@Service("CandidatoServiceImpList")
public class CandidatoServiceImp implements ICandidatoService{
	
	@Autowired
	private ListaCandidato listaCandidato;

	@Override
	public Candidato getCandidato() {
		// retorna un nuevo objeto de la clase Candidato
		return new Candidato();
	}

	@Override
	public boolean guardarCandidato(Candidato candidato) {
		// guarda un objeto candidato en la lista de candidatos
		// devuelve true si se guardo correctamente sino devuelve false
		return listaCandidato.getCandidatos().add(candidato);
	}

	@Override
	public void modificarCandidato(Candidato candidato) {
		// busca el candidato por su codigo y actualiza sus atributos
		// solo actualiza Nombre, GeneroMusical y Descripcion
		for(Candidato cand:  listaCandidato.getCandidatos()) {
			if(cand.getCodigo() == candidato.getCodigo()) {
				cand.setNombre(candidato.getNombre());
				cand.setGeneroMusical(candidato.getGeneroMusical());
				cand.setDescripcion(candidato.getDescripcion());
			}
		}
		
	}
	
	@Override
	public boolean sumarVotoCandidato(int codigo) {
		// busca el candidato por su codigo y actualiza sus atributos
		// solo actualiza Nombre, GeneroMusical y Descripcion
		boolean aux = false;
		for(Candidato cand:  listaCandidato.getCandidatos()) {
			if(cand.getCodigo() == codigo) {
				aux = cand.sumarVoto();
			}
		}
		return aux;
		
	}

	@Override
	public void eliminarCandidato(int codigo) {
		// elimina un Candidato de acuerdo al codigo del candidato
		Optional<Candidato> candidato = listaCandidato.getCandidatos().stream().filter(c -> c.getCodigo() == codigo).findFirst();
		listaCandidato.getCandidatos().remove(candidato.get());
	}

	@Override
	public ListaCandidato getListaCandidato() {
		// devuelve la lista de Candidatos
		return listaCandidato;
	}

	@Override
	public Candidato buscarCandidato(int codigo) {
		// busca un candidato por codigo y lo devuelve el objeto relacionado al codigo
		Optional<Candidato> candidato = listaCandidato.getCandidatos().stream().filter(c -> c.getCodigo() == codigo).findFirst();
		return candidato.get();
	}
	
	@Override
	public int calcularTotalVotos() {
		return listaCandidato.getCandidatos().stream().mapToInt(c -> c.getCantidadVotos()).sum();
	}

}
