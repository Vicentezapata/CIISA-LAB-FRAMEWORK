package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Ingrediente;
import com.example.demo.entity.Medida;

public interface MedidaService {

    public List<Medida> findAll();

	public void save(Medida medida);

	public Medida findOne(Long id);

	public void delete(Long id);

	public List<Medida> findByNombre(String term);
}
