package com.example.demo.model;

public class LibroDTO {
	
	private int identificador;
	private String titulo=null;
	private String autor=null;
	private String publicadoen=null;
	private int isbn;
	private String estado="Disponible";
	
	/*constructores*/
	public LibroDTO() {
		
	}
	
	public LibroDTO(int identificador,String titulo, String autor, String publicadoen, int isbn) {
		super();
		this.identificador = identificador;
		this.titulo = titulo;
		this.autor = autor;
		this.publicadoen = publicadoen;
		this.isbn = isbn;
	}
	/*getters y setters*/
	
	public int getIdentificador() {
		return identificador;
	}
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getPublicadoen() {
		return publicadoen;
	}
	public void setPublicadoen(String publicadoen) {
		this.publicadoen = publicadoen;
	}
	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return "LibroDTO [identificador=" + identificador + ", titulo=" + titulo + ", autor=" + autor + ", publicadoen="
				+ publicadoen + ", isbn=" + isbn + ", estado=" + estado + "]";
	}
	
	
	
}
