package com.personal.ofm.service;

import org.springframework.stereotype.Service;

import com.personal.ofm.entity.Clientes;
import com.personal.ofm.repository.IClientes;

@Service
public class ClientesService {

	/*SE CREA UN OBJETO DE EL REPOSITORIO, QUE YA INCLUDE EL CRUD*/
	private IClientes ic;
	
	/*PARA GUARDAR*/
	public Boolean saveCliente(Clientes cliente) {
		Boolean bandera = true;
		
		try {
			ic.save(cliente);
		} catch (Exception e) {
			bandera = false;
		}
		return bandera;
	}
}
