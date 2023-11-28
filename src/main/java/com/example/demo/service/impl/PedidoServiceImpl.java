package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.dao.IPedidoDao;
import com.example.demo.entity.Pedido;
import com.example.demo.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService{

    @Autowired
	private IPedidoDao iPedidoDao;

    @Transactional(readOnly = true)
	public List<Pedido> findAll() {
		return (List<Pedido>) iPedidoDao.findAll();
	}

	@Transactional
	public void save(Pedido ingrediente) {
		iPedidoDao.save(ingrediente);

	}

	@Transactional(readOnly = true)
	public Pedido findOne(Long id) {
		// TODO Auto-generated method stub
		return iPedidoDao.findById(id).orElse(null);
	}

	@Transactional
	public void delete(Long id) {
		iPedidoDao.deleteById(id);
	}

	@Override
	public List<Pedido> findByNombre(String term) {
		// TODO Auto-generated method stub
		return null;
	}

}
