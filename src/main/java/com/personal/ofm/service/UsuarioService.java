package com.personal.ofm.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.ofm.entity.Roles;
import com.personal.ofm.entity.Usuarios;
import com.personal.ofm.repository.IRoles;
import com.personal.ofm.repository.IUsuarios;

@Service
public class UsuarioService {

	@Autowired
	IUsuarios iusuarios;
	@Autowired
	IRoles iroles;
	
	@Transactional
	public List<Usuarios> listUsuarios(){
		return (List<Usuarios>) iusuarios.findAll();
	}
	
	@Transactional
	public Boolean saveOrUpdate(Usuarios usuarios) {
		try {
			iusuarios.save(usuarios);
			return true;
		} catch (Exception e) {
			return false;
			
		}
	}

	@Transactional
	public Boolean delete(Usuarios usuarios) {
		try {
			iusuarios.delete(usuarios);
			return true;
		} catch (Exception e) {
			return false;
			
		}
	}
	
	@Transactional
	public Usuarios getIdUsuario(Long id) {
		return iusuarios.findById(id).get(); 
	}
	
	@Transactional
	public Roles getIdRol(int id) {
		return iroles.findById(id).get();
	}
}
