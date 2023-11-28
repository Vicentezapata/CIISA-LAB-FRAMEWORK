package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.IMedidaDao;
import com.example.demo.entity.Medida;
import com.example.demo.service.MedidaService;

@Service
public class MedidaServiceImpl implements MedidaService{

    @Autowired
	private IMedidaDao iMedidaDao;

    @Transactional(readOnly = true)
	public List<Medida> findAll() {
		return (List<Medida>) iMedidaDao.findAll();
	}

	@Transactional
	public void save(Medida ingrediente) {
		iMedidaDao.save(ingrediente);

	}

	@Transactional(readOnly = true)
	public Medida findOne(Long id) {
		// TODO Auto-generated method stub
		return iMedidaDao.findById(id).orElse(null);
	}

	@Transactional
	public void delete(Long id) {
		iMedidaDao.deleteById(id);
	}

	@Override
	public List<Medida> findByNombre(String term) {
		// TODO Auto-generated method stub
		return null;
	}

}
