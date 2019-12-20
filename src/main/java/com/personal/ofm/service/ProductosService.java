package com.personal.ofm.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.ofm.entity.Categorias;
import com.personal.ofm.entity.Productos;
import com.personal.ofm.repository.ICategorias;
import com.personal.ofm.repository.IProductos;

@Service
public class ProductosService {
	@Autowired
	IProductos iproductos;
	@Autowired
	ICategorias icategorias;

	@Transactional
	public Boolean saveOrUpdateProducto(Productos producto) {
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
	public Boolean Delete(Productos productos) {
		try {
			iproductos.delete(productos);
			return true;
		} catch (Exception e) {
			return false;
			
		}
	}
	
	@Transactional
	public Productos getIdProducto(Long id) {
		return iproductos.findById(id).get();
	}
	
	@Transactional
	public Categorias getIdCategoria(Long id) {
		return icategorias.findById(id).get();
	}
	
	
	@Transactional
	public List<Productos> ListProductoByCategpria(String categoria){
		return iproductos.findByCategoria(categoria);
	}
	
}
