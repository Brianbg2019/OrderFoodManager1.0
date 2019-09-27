package com.personal.ofm.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.personal.ofm.entity.Usuarios;

@Repository
public interface IUsuarios extends CrudRepository<Usuarios, Long> {
}
