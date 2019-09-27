package com.personal.ofm.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.personal.ofm.entity.Productos;

@Repository
public interface IProductos extends CrudRepository<Productos, Long> {

}
