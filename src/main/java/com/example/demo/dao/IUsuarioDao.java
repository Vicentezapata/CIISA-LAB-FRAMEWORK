package com.example.demo.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.entity.Medida;
import com.example.demo.entity.Pedido;
import com.example.demo.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{
	Usuario findByEmail(String email);
}
