package com.personal.ofm.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.personal.ofm.entity.Clientes;

@Repository
public interface IClientes extends CrudRepository<Clientes, Long> {

}
