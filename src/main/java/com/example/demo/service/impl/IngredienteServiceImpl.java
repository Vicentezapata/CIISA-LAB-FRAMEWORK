package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.ICoctelesIngredientesDao;
import com.example.demo.dao.IIngredientesDao;
import com.example.demo.entity.CoctelesIngredientes;
import com.example.demo.entity.Ingrediente;
import com.example.demo.service.IngredienteService;

@Service
public class IngredienteServiceImpl implements IngredienteService{

    @Autowired
	private IIngredientesDao iIngredientesDao;
    
    @Autowired
	private ICoctelesIngredientesDao coctelIngredientesDao;

    @Transactional(readOnly = true)
	public List<Ingrediente> findAll() {
		return (List<Ingrediente>) iIngredientesDao.findAll();
	}

	@Transactional
	public void save(Ingrediente ingrediente) {
		iIngredientesDao.save(ingrediente);

	}

	@Transactional(readOnly = true)
	public Ingrediente findOne(Long id) {
		// TODO Auto-generated method stub
		return iIngredientesDao.findById(id).orElse(null);
	}

	@Transactional
	public void delete(Long id) {
		iIngredientesDao.deleteById(id);
	}

	@Override
	public List<Ingrediente> findByNombre(String term) {
		// TODO Auto-generated method stub
		return iIngredientesDao.findByNombre(term);
	}

	@Override
	public List<CoctelesIngredientes> findCoctelIngById(Long id) {
		// TODO Auto-generated method stub
		return coctelIngredientesDao.findByIngredienteId(id);
	}

}
