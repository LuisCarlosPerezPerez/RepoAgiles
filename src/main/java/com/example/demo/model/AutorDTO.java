package com.example.demo.model;

import java.util.List;

public class AutorDTO {
	
	private String Nombre;
	private String Apellidos;
	
	
	public AutorDTO() {
		super();
	}
	
	
	
	public AutorDTO(String nombre, String apellidos) {
		super();
		this.Nombre = nombre;
		this.Apellidos = apellidos;
	}



	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellidos() {
		return Apellidos;
	}
	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}



	@Override
	public String toString() {
		return "AutorDTO [Nombre=" + Nombre + ", Apellidos=" + Apellidos + "]";
	}







	
}
