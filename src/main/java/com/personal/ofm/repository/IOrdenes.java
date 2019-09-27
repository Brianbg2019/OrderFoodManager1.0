package com.personal.ofm.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.personal.ofm.entity.Ordenes;

@Repository
public interface IOrdenes extends CrudRepository<Ordenes, Long> {

}
