package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.CoctelesIngredientes;
import com.example.demo.entity.Ingrediente;

public interface IngredienteService {

    public List<Ingrediente> findAll();

	public void save(Ingrediente ingrediente);

	public Ingrediente findOne(Long id);

	public void delete(Long id);

	public List<Ingrediente> findByNombre(String term);
	
	public List<CoctelesIngredientes> findCoctelIngById(Long id);
}
