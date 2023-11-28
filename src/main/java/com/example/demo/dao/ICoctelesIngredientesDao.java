package com.example.demo.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.CoctelesIngredientes;

public interface ICoctelesIngredientesDao extends CrudRepository<CoctelesIngredientes, Long>{

	List<CoctelesIngredientes> findByIngredienteId(Long id);
	List<CoctelesIngredientes> findByCoctelId(Long id);

}
