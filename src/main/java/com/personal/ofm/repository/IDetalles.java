package com.personal.ofm.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.personal.ofm.entity.Detalles;

@Repository
public interface IDetalles extends CrudRepository<Detalles, Long> {

}
