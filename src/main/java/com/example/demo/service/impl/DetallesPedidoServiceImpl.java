package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.IDetallesPedidoDao;
import com.example.demo.dao.IPedidoDao;
import com.example.demo.entity.CoctelesIngredientes;
import com.example.demo.entity.DetallesPedido;
import com.example.demo.entity.Pedido;
import com.example.demo.service.DetallesPedidoService;
import com.example.demo.service.PedidoService;

@Service
public class DetallesPedidoServiceImpl implements DetallesPedidoService{

    @Autowired
	private IDetallesPedidoDao iDetallesPedidoDao;

    @Transactional(readOnly = true)
	public List<DetallesPedido> findAll() {
		return (List<DetallesPedido>) iDetallesPedidoDao.findAll();
	}

	@Transactional
	public void save(DetallesPedido ingrediente) {
		iDetallesPedidoDao.save(ingrediente);

	}

	@Transactional(readOnly = true)
	public DetallesPedido findOne(Long id) {
		// TODO Auto-generated method stub
		return iDetallesPedidoDao.findById(id).orElse(null);
	}

	@Transactional
	public void delete(Long id) {
		iDetallesPedidoDao.deleteById(id);
	}

	@Override
	public List<DetallesPedido> findByPedidoId(Long id) {
		// TODO Auto-generated method stub
		return iDetallesPedidoDao.findByPedidoId(id);
	}
	

}
