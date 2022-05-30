package ar.edu.unju.fi.model;

import java.time.LocalDate;
import java.time.Period;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class Usuario {
	@Size(min=3, max=80, message="El nombre debe tener entre 3 a 80 caracteres")
	@NotEmpty(message="El nombre del usuario no puede estar vacío")
	private String nombre;
	@NotEmpty(message="El email del usuario no puede estar vacío")
	@Email
	private String email;
//	@NotEmpty(message = "La fecha de nacimento es obligatoria")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaNacimiento;
	private int votosEmitidos;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario(String nombre, String email, LocalDate fechaNacimiento) {
		this.nombre = nombre;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.votosEmitidos = 0;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public boolean emitirVoto() {
		if(this.votosEmitidos >= 3) {
			return false;
		}else {
			this.votosEmitidos++;
			return true;
		}
	}
	public int calcularEdad() {
		LocalDate fechaHoy = LocalDate.now();
		return Period.between(this.fechaNacimiento, fechaHoy).getYears();
	}
}
