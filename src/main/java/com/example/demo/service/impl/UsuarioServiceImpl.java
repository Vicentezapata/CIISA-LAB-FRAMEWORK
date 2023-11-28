package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.IDetallesPedidoDao;
import com.example.demo.dao.IPedidoDao;
import com.example.demo.dao.IUsuarioDao;
import com.example.demo.entity.DetallesPedido;
import com.example.demo.entity.Pedido;
import com.example.demo.entity.Usuario;
import com.example.demo.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
	private IUsuarioDao iUsuarioDao;
    
    @Autowired
	private IPedidoDao iPedidoDao;
    

    @Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return (List<Usuario>) iUsuarioDao.findAll();
	}

	@Transactional
	public void save(Usuario ingrediente) {
		iUsuarioDao.save(ingrediente);

	}

	@Transactional(readOnly = true)
	public Usuario findOne(Long id) {
		// TODO Auto-generated method stub
		return iUsuarioDao.findById(id).orElse(null);
	}

	@Transactional
	public void delete(Long id) {
		iUsuarioDao.deleteById(id);
	}

	@Override
	public List<Usuario> findByNombre(String term) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> findPedidosById(Long id) {
		// TODO Auto-generated method stub
		return iPedidoDao.findByUsuarioId(id);
	}

}
