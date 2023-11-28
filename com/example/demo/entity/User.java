package com.example.demo.entity;

public class User{ 
		
		private String nombre;
		private Long userId;
		private String aPaterno;
		private String aMaterno;
		private String email;
		private String rut;
		private String rol;
		
		public User() {
		}
		
		
		public User(String nombre, Long userId, String aPaterno, String aMaterno, String email, String rut, String rol) {
			this.nombre = nombre;
			this.userId = userId;
			this.aPaterno = aPaterno;
			this.aMaterno = aMaterno;
			this.email = email;
			this.rut = rut;
			this.rol = rol;
		}
		
		
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public Long getUserId() {
			return userId;
		}
		public void setUserId(Long userId) {
			this.userId = userId;
		}
		public String getaPaterno() {
			return aPaterno;
		}
		public void setaPaterno(String aPaterno) {
			this.aPaterno = aPaterno;
		}
		public String getaMaterno() {
			return aMaterno;
		}
		public void setaMaterno(String aMaterno) {
			this.aMaterno = aMaterno;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getRut() {
			return rut;
		}
		public void setRut(String rut) {
			this.rut = rut;
		}
		public String getRol() {
			return rol;
		}
		public void setRol(String rol) {
			this.rol = rol;
		}
		
	}

