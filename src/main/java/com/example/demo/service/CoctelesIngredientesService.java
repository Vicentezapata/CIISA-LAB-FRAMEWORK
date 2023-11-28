package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Coctel;
import com.example.demo.entity.CoctelesIngredientes;

@Service
public interface CoctelesIngredientesService {
    public List<CoctelesIngredientes> findAll();
    
    public List<CoctelesIngredientes> findByCoctelId(Long id);

	public void save(CoctelesIngredientes coctelesIngredientes);

	public CoctelesIngredientes findOne(Long id);

	public void delete(Long id);

	public List<CoctelesIngredientes> findByNombre(String term);

	
}
