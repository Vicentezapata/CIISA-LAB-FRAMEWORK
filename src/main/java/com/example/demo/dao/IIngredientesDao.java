package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Ingrediente;

@Repository
public interface IIngredientesDao extends CrudRepository<Ingrediente, Long>{
	
	@Query("select i from Ingrediente i where i.nombre like %:nombre%")
	public List<Ingrediente> findByNombre(@Param("nombre") String term);

	public List<Ingrediente> findByNombreLikeIgnoreCase(String term);

}
