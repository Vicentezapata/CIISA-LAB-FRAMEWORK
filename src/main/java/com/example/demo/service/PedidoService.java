package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Pedido;

public interface PedidoService {

    public List<Pedido> findAll();

	public void save(Pedido usuario);

	public Pedido findOne(Long id);

	public void delete(Long id);

	public List<Pedido> findByNombre(String term);
}
