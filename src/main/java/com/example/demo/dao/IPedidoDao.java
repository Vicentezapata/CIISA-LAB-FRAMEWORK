package com.example.demo.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.example.demo.entity.Pedido;

public interface IPedidoDao extends CrudRepository<Pedido, Long>{

	List<Pedido> findByUsuarioId(Long id);

}
