package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Estado;

public interface EstadoService {

    public List<Estado> findAll();

	public void save(Estado medida);

	public Estado findOne(Long id);

	public void delete(Long id);

	public List<Estado> findByNombre(String term);
}
