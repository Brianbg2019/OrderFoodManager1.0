package com.personal.ofm.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.personal.ofm.entity.Roles;

@Repository
public interface IRoles extends CrudRepository<Roles, Integer> {

}
