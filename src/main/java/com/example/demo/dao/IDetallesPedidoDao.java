package com.example.demo.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.entity.CoctelesIngredientes;
import com.example.demo.entity.DetallesPedido;

public interface IDetallesPedidoDao extends CrudRepository<DetallesPedido, Long>{
	
	List<DetallesPedido> findByCoctelId(Long id);
	
	List<DetallesPedido> findByPedidoId(Long id);
	

}
