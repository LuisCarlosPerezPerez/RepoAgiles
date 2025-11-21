package com.example.demo.model;

public class Cantidad {
	
	private LibroDTO libro;
	
	private int cantidad;
	

	public Cantidad() {
		super();
	}



	public Cantidad(LibroDTO libro, int cantidad) {
		super();
		this.libro = libro;
		this.cantidad = cantidad;
	}



	public LibroDTO getLibro() {
		return libro;
	}



	public void setLibro(LibroDTO libro) {
		this.libro = libro;
	}



	public int getCantidad() {
		return cantidad;
	}



	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}



	@Override
	public String toString() {
		return "Cantidad [libro=" + libro + ", cantidad=" + cantidad + "]";
	}
	
	
}
