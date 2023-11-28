package com.example.demo.models;

public class Categoria {

	private String nombreCategoria;
	private int categoriaId;
	
	public Categoria() {
	}

	public Categoria(String nombreCategoria, int categoriaId) {
		this.nombreCategoria = nombreCategoria;
		this.categoriaId = categoriaId;
	}
	
	public String getNombreCategoria() {
		return nombreCategoria;
	}
	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}
	public int getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(int categoriaId) {
		this.categoriaId = categoriaId;
	}
	
	
}


