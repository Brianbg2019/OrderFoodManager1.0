package com.personal.ofm.service;
import org.springframework.stereotype.Service;
import com.personal.ofm.entity.Roles;
import com.personal.ofm.repository.IRoles;

@Service
public class RolesService {

	//CREO UN OBJETO DEL REPOSITORIO QUE TIENE YA INYECTADO EL CRUD
	private IRoles rol;

	//CREAT
	public Boolean saveRol(Roles roles) {
		Boolean bandera = true;

		try {
			rol.save(roles);
		} catch (Exception e) {
			bandera = false;
		}
		return bandera;
	}
}
