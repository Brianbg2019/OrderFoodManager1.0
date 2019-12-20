package com.personal.ofm.service;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.personal.ofm.entity.Roles;
import com.personal.ofm.repository.IRoles;

@Service
public class RolesService {

	//CREO UN OBJETO DEL REPOSITORIO QUE TIENE YA INYECTADO EL CRUD
	@Autowired
	 IRoles iroles;

	//CREAT
	 @Transactional
	public Boolean saveOrUpdateRol(Roles roles) {
		Boolean bandera = true;

		try {
			iroles.save(roles);
		} catch (Exception e) {
			bandera = false;
		}
		return bandera;
	}
	
	@Transactional
	public List<Roles> listRoles(){
		return (List<Roles>) iroles.findAll();
	}
	
	@Transactional
	public Boolean Delete(Roles roles) {
		try {
			iroles.delete(roles);
			return true;
		} catch (Exception e) {
			return false;
			
		}
	}
	
	@Transactional
	public Roles getIdRol(Integer id) {
		return iroles.findById(id).get();
	}
	
	
}
