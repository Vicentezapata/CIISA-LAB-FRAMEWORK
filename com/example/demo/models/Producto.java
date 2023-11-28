package com.example.demo.models;

public class Producto {
		private String nombreProducto;
		private int productoId;
		private int categoriaId;
		private int distribuidoresId;
		private int estadoId;
		private int ofertasId;
		private int stock;
		private int valor;
		private String descripcion;
		
		
		public Producto() {
		}
		
				
		public Producto(String nombreProducto, int productoId, String descripcion, int categoriaId,
				int distribuidoresId, int estadoId, int ofertasId, int stock, int valor) {
			this.nombreProducto = nombreProducto;
			this.productoId = productoId;
			this.descripcion = descripcion;
			this.categoriaId = categoriaId;
			this.distribuidoresId = distribuidoresId;
			this.estadoId = estadoId;
			this.ofertasId = ofertasId;
			this.stock = stock;
			this.valor = valor;
		}


		public String getNombreProducto() {
			return nombreProducto;
		}
		public void setNombreProducto(String nombreProducto) {
			this.nombreProducto = nombreProducto;
		}
		public int getProductoId() {
			return productoId;
		}
		public void setProductoId(int productoId) {
			this.productoId = productoId;
		}
		public String getDescripcion() {
			return descripcion;
		}
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
		public int getCategoriaId() {
			return categoriaId;
		}
		public void setCategoriaId(int categoriaId) {
			this.categoriaId = categoriaId;
		}
		public int getDistribuidoresId() {
			return distribuidoresId;
		}
		public void setDistribuidoresId(int distribuidoresId) {
			this.distribuidoresId = distribuidoresId;
		}
		public int getEstadoId() {
			return estadoId;
		}
		public void setEstadoId(int estadoId) {
			this.estadoId = estadoId;
		}
		public int getOfertasId() {
			return ofertasId;
		}
		public void setOfertasId(int ofertasId) {
			this.ofertasId = ofertasId;
		}
		public int getStock() {
			return stock;
		}
		public void setStock(int stock) {
			this.stock = stock;
		}
		public int getValor() {
			return valor;
		}
		public void setValor(int valor) {
			this.valor = valor;
		}
		
		
		
		
}
		
		
		
		

	

