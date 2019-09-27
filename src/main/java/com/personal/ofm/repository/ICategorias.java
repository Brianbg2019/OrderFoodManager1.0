package com.personal.ofm.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.personal.ofm.entity.Categorias;

@Repository
public interface ICategorias extends CrudRepository<Categorias, Long> {

}
