package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.ICoctelDao;
import com.example.demo.dao.ICoctelesIngredientesDao;
import com.example.demo.entity.Coctel;
import com.example.demo.entity.CoctelesIngredientes;
import com.example.demo.service.CoctelService;
import com.example.demo.service.CoctelesIngredientesService;

@Service
public class CoctelesIngredientesServiceImpl implements CoctelesIngredientesService{

    @Autowired
	private ICoctelesIngredientesDao coctelesIngredientesDao;

    @Transactional(readOnly = true)
	public List<CoctelesIngredientes> findAll() {
		return (List<CoctelesIngredientes>) coctelesIngredientesDao.findAll();
	}

	@Transactional
	public void save(CoctelesIngredientes coctelesIngredientes) {
		coctelesIngredientesDao.save(coctelesIngredientes);

	}

	@Transactional(readOnly = true)
	public CoctelesIngredientes findOne(Long id) {
		// TODO Auto-generated method stub
		return coctelesIngredientesDao.findById(id).orElse(null);
	}

	@Transactional
	public void delete(Long id) {
		coctelesIngredientesDao.deleteById(id);
	}

	@Override
	public List<CoctelesIngredientes> findByNombre(String term) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<CoctelesIngredientes> findByCoctelId(Long id) {
		// TODO Auto-generated method stub
		return coctelesIngredientesDao.findByCoctelId(id);
	}

}
