package com.personal.ofm.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.personal.ofm.entity.Productos;
import com.personal.ofm.repository.IProductos;

@Service
public class ProductosService {
	@Autowired
	IProductos iproductos;

	@Transactional
	public Boolean saveProducto(Productos producto) {
		Boolean response = true;
		
		try {
			iproductos.save(producto);
		} catch (Exception e) {
			response = false;
		}
		return response;
	}
	
	@Transactional
	public List<Productos> ListProducto(){
		return ((List<Productos>) iproductos.findAll());
	}
	@Transactional
	public List<Productos> ListProductoByCategpria(String categoria){
		return iproductos.findByCategoria(categoria);
	}
	
}
