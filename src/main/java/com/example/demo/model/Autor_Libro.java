package com.example.demo.model;

import java.util.List;

public class Autor_Libro {

	private AutorDTO autor;
	private LibroDTO libro;
	
	
	
	public Autor_Libro() {
		super();
	}
	
	public Autor_Libro(AutorDTO autor, LibroDTO libro) {
		super();
		this.autor = autor;
		this.libro = libro;
	}
	
	public AutorDTO getAutor() {
		return autor;
	}
	public void setAutor(AutorDTO autor) {
		this.autor = autor;
	}
	public LibroDTO getLibro() {
		return libro;
	}
	public void setLibros(LibroDTO libro) {
		this.libro = libro;
	}

	@Override
	public String toString() {
		return "Autor_Libro [autor=" + autor + ", libro=" + libro + "]";
	}
	
	
	
}
