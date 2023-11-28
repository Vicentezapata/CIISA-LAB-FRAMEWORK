package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.DetallesPedido;

public interface DetallesPedidoService {

    public List<DetallesPedido> findAll();

	public void save(DetallesPedido usuario);

	public DetallesPedido findOne(Long id);

	public void delete(Long id);

	public List<DetallesPedido> findByPedidoId(Long id);
}
