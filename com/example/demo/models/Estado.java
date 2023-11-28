package com.example.demo.models;

public class Estado {

	
	private String nombreEstado;
	private int estadoId;
	
	public Estado() {
	}
	
	
	public Estado(String nombreEstado, int estadoId) {
		this.nombreEstado = nombreEstado;
		this.estadoId = estadoId;
	}
	
	
	public String getNombreEstado() {
		return nombreEstado;
	}
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}
	public int getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(int estadoId) {
		this.estadoId = estadoId;
	}
	
	
	
}
