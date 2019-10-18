package com.personal.ofm.repository;
import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.personal.ofm.entity.Productos;

@Repository
public interface IProductos extends CrudRepository<Productos, Long> {

	 @Query("select u from Productos u where u.idCategoria.nombreCategoria =:categoria")
	  List<Productos> findByCategoria(@Param("categoria") String categoria);
}
