package ar.edu.unju.fi.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Candidato {
	@Min(value=1,message="El Codigo debe ser mayor a 0")
	private int codigo;
	@Size(min=3, max=80, message="El nombre debe tener entre 3 a 80 caracteres")
	@NotEmpty(message="El nombre del candidato no puede estar vacío")
	private String nombre;
	@Size(min=3, max=80, message="El genero musical debe tener entre 3 a 80 caracteres")
	@NotEmpty(message="El genero musical no puede estar vacío")
	private String generoMusical;
	@Size(min=20, max=200, message="La descripcion debe tener entre 20 a 200 caracteres")
	@NotEmpty(message="La descripcion no puede estar vacío")
	private String descripcion;
	private int cantidadVotos;

	public Candidato() {
		// TODO Auto-generated constructor stub
	}

	public Candidato(int codigo, String nombre, String generoMusical, String descripcion) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.generoMusical = generoMusical;
		this.descripcion = descripcion;
		this.cantidadVotos = 0;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGeneroMusical() {
		return generoMusical;
	}

	public void setGeneroMusical(String generoMusical) {
		this.generoMusical = generoMusical;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCantidadVotos() {
		return cantidadVotos;
	}

	public void setCantidadVotos(int cantidadVotos) {
		this.cantidadVotos = cantidadVotos;
	}
	
	public boolean sumarVoto() {
		this.cantidadVotos++;
		return true;
	}
	public float calcularPorcentaje(int totalVotos) {
		if (totalVotos>0) {
			return 100*this.cantidadVotos/totalVotos;
		}else {
			return 0;
		}
			
	}
	

}
