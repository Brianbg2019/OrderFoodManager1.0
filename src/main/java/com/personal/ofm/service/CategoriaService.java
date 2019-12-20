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
public class CategoriaService {
	@Autowired
	ICategorias icategoria;
	@Autowired
	IProductos iproducto;
	@Transactional
	public boolean saveCategoria(Categorias categoria, Productos producto) {
		Boolean response = true;
		producto.setIdCategoria(categoria);
		try {
			icategoria.save(categoria);
		} catch (Exception e) {
			response = false;
		}
		return response;
	}
	
	@Transactional
	public List<Categorias> ListCategoria(){
		return (List<Categorias>) icategoria.findAll();
	}
	
	@Transactional
	public Boolean SaveOrUpdate(Categorias categoria) {
		try {
			icategoria.save(categoria);
			return true;
		} catch (Exception e) {
			return false;
			
		}
	}
	
	@Transactional
	public boolean deleteCategoria(Long id){
		boolean response = true;
		try {
			icategoria.deleteById(id);
		} catch (Exception e) {
			 response = false;
		}
		return response;
	}
	
	@Transactional
	public Categorias getIdCategoria(Long id) {
		return icategoria.findById(id).get();
	}
}
