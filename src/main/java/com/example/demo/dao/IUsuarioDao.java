package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.entity.Medida;
import com.example.demo.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{

}
