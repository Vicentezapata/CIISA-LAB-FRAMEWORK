package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Coctel;
import com.example.demo.entity.CoctelesIngredientes;
import com.example.demo.entity.DetallesPedido;

@Service
public interface CoctelService {
    public List<Coctel> findAll();

	public void save(Coctel coctel);

	public Coctel findOne(Long id);

	public void delete(Long id);

	public List<Coctel> findByNombre(String term);
	
	public List<CoctelesIngredientes> findCoctelIngById(Long id);
	
	public List<DetallesPedido> findDetallesPedidosById(Long id);
	
}
