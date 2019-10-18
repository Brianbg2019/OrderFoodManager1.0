package com.personal.ofm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.personal.ofm.entity.Detalles;
import com.personal.ofm.repository.IDetalles;
import com.personal.ofm.repository.IProductos;

@Controller
@RequestMapping("detalle")
public class DetalleController {
	
	@Autowired
	IDetalles idetalles;
	@Autowired
	IProductos iproductos;
	
	@GetMapping(value = "vista")
	public String vista(Model m) {
		List<Detalles> detalles = (List<Detalles>) idetalles.findAll();
		m.addAttribute("items", detalles);
		
	return "vistaCliente/index";
	}
	@GetMapping(value = "mostrar")
	public String Mostrar(Model m) {
		List<Detalles> detalles = (List<Detalles>) idetalles.findAll();
		m.addAttribute("items", detalles);
		
	return "vistaCliente/ordenes";
	
	}
	

}
