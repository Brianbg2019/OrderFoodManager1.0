package com.personal.ofm.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.ofm.entity.Clientes;
import com.personal.ofm.repository.IClientes;

@Service
public class ClientesService {

	/*SE CREA UN OBJETO DE EL REPOSITORIO, QUE YA INCLUDE EL CRUD*/
	@Autowired
	 IClientes iclientes;
	
	/*PARA GUARDAR*/
	public Boolean saveOrUpdate(Clientes cliente) {
		Boolean bandera = true;
		
		try {
			iclientes.save(cliente);
		} catch (Exception e) {
			bandera = false;
		}
		return bandera;
	}
	
	@Transactional
	public List<Clientes> listClientes(){
		return (List<Clientes>) iclientes.findAll();
	}
	
	public Boolean delete(Clientes cliente) {
		try {
			iclientes.delete(cliente);
			return true;
		} catch (Exception e) {
			return false;
			
		}
		
	}
	@Transactional
	public Clientes getIdCliente(Long id) {
		return iclientes.findById(id).get();
	}
}
