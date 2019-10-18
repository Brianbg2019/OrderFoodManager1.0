package com.personal.ofm.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.ofm.entity.Clientes;
import com.personal.ofm.repository.IClientes;

@Service
public class VentasService {
	
	@Autowired
	IClientes iclientes;
	
	@Transactional
	public Clientes getCliente(Long id) {
		return iclientes.findById(id).get();
	}
}
