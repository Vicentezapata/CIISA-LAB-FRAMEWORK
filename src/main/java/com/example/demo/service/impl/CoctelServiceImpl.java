package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.ICoctelDao;
import com.example.demo.dao.ICoctelesIngredientesDao;
import com.example.demo.dao.IDetallesPedidoDao;
import com.example.demo.entity.Coctel;
import com.example.demo.entity.CoctelesIngredientes;
import com.example.demo.entity.DetallesPedido;
import com.example.demo.service.CoctelService;

@Service
public class CoctelServiceImpl implements CoctelService{

    @Autowired
	private ICoctelDao iCoctelDao;
    
    @Autowired
	private ICoctelesIngredientesDao coctelIngredientesDao;
    
    @Autowired
	private IDetallesPedidoDao detallesPedidoDao;

    @Transactional(readOnly = true)
	public List<Coctel> findAll() {
		return (List<Coctel>) iCoctelDao.findAll();
	}

	@Transactional
	public void save(Coctel ingrediente) {
		iCoctelDao.save(ingrediente);

	}

	@Transactional(readOnly = true)
	public Coctel findOne(Long id) {
		// TODO Auto-generated method stub
		return iCoctelDao.findById(id).orElse(null);
	}

	@Transactional
	public void delete(Long id) {
		iCoctelDao.deleteById(id);
	}

	@Override
	public List<Coctel> findByNombre(String term) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CoctelesIngredientes> findCoctelIngById(Long id) {
		// TODO Auto-generated method stub
		return coctelIngredientesDao.findByCoctelId(id);
	}

	@Override
	public List<DetallesPedido> findDetallesPedidosById(Long id) {
		// TODO Auto-generated method stub
		return detallesPedidoDao.findByCoctelId(id);
	}
	

}
